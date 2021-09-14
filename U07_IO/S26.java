package U07_IO;

import org.imgscalr.Scalr;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;

public class S26 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS26.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

class UnitTestS26{
    @Test
    public void resizeImageTest() throws IOException{
        File files = new File("./src/main/resources");
        File testFolder = new File("./src/test/images");
        testFolder.mkdir();
        Utils.copyFolder(files.getAbsolutePath(), testFolder.getAbsolutePath());
        ImageResizer.resize(testFolder.getAbsolutePath(), 100);
        for(File file : testFolder.listFiles()){
            BufferedImage image = ImageIO.read(file);
            Assert.assertEquals(100, image.getWidth());
        }
        FileUtils.deleteDirectory(testFolder);
    }

    @Test(expected = NullPointerException.class)
    public void nullTest(){
        ImageResizer.resize(null, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notExistedFolderTest(){
        ImageResizer.resize("NotExistedFolder", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongSizeTest(){
        File files = new File("./src/main/resources");
        File testFolder = new File("./src/test/images");
        testFolder.mkdir();
        Utils.copyFolder(files.getAbsolutePath(), testFolder.getAbsolutePath());
        ImageResizer.resize(testFolder.getAbsolutePath(), -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyFolderTest() throws IOException{
        File testFolder = new File("./src/test/images");
        testFolder.mkdir();
        ImageResizer.resize(testFolder.getAbsolutePath(), 100);
        FileUtils.deleteDirectory(testFolder);
    }


    static class Utils {
        private static ArrayList<Path> outPaths;
        private static ArrayList<Path> inPaths;
        private static String mainDirectory;
        private static String destDirectory;


        public static void copyFolder(String sourceDirectory, String destinationDirectory) {
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
}

class ImageResizer {

    public static void resize(String path, int width){
        File file = new File(path);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            resizeImages(files, width);
        }
        else{
            throw new IllegalArgumentException("Неверный формат папки");
        }
    }

    private static void resizeImages(File[] files, int width) {
        Arrays.stream(files).forEach(file -> {
            if (file != null){
                try {
                    BufferedImage image = ImageIO.read(file);
                    double k = (double) image.getWidth() / image.getHeight();
                    int newHeight = (int) (width / k);
                    BufferedImage result = Scalr.resize(image, width, newHeight);
                    ImageIO.write(result, "jpg", file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}