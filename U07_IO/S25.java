package U07_IO;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S25 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS25.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

class UnitTestS25{

    @Test
    public void copyTest() throws IOException {
        final File folder = new File(System.getProperty("user.dir"));
        final File destFolder = new File(folder.getParent().concat("\\TestFolder"));
        Utils.copyFolder(folder.getAbsolutePath(), destFolder.getAbsolutePath());
        Assert.assertEquals(Utils.calculateFolderSize(folder.getAbsolutePath()), Utils.calculateFolderSize(destFolder.getAbsolutePath()), 10e-5);
        FileUtils.deleteDirectory(destFolder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notExistedFolderCopyTest() {
        final File folder = new File("NotExistedFolder");
        final File destFolder = new File(System.getProperty("user.dir").concat("\\TestFolder"));
        Utils.copyFolder(folder.getAbsolutePath(), destFolder.getAbsolutePath());
    }
}

class Utils {
    private static List<File> files;
    private static ArrayList<Path> outPaths;
    private static ArrayList<Path> inPaths;
    private static String mainDirectory;
    private static String destDirectory;

    public static long calculateFolderSize(String path){
        if(!path.matches("([a-zA-Z]:)?((\\\\(\\w ?)+)+)?")) throw new IllegalArgumentException("Неверный формат пути");
        File folder = new File(path);
        files = new ArrayList<>();
        getFiles(folder);
        return files.stream().mapToLong(File::length).sum();
    }

    private static void getFiles (File folder){
        if (!folder.isDirectory()) files.add(folder);
        else {
            Arrays.stream(folder.listFiles()).forEach(Utils::getFiles);
        }
    }

    public static void copyFolder(String sourceDirectory, String destinationDirectory){
        mainDirectory = sourceDirectory;
        destDirectory = destinationDirectory;
        outPaths = new ArrayList<>();
        inPaths = new ArrayList<>();
        File file = new File(sourceDirectory);
        if (!file.exists()) throw new IllegalArgumentException("Ошибка ввода");
        try {
            Files.walkFileTree(Paths.get(sourceDirectory), new MyFileVisitor());
            for (int i = 0; i < inPaths.size(); i++) {
                Files.createDirectories(outPaths.get(i));
                Files.copy(inPaths.get(i), outPaths.get(i), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Path getPath(Path path) {
        String dir = path.toString().replace(mainDirectory, destDirectory);
        return Paths.get(dir);
    }

    public static class MyFileVisitor extends SimpleFileVisitor {

        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            inPaths.add((Path) file);
            outPaths.add(getPath((Path) file));
            return FileVisitResult.CONTINUE;
        }
    }
}
