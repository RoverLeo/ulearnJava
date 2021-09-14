package U07_IO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S24 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS24.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

class UnitTestS24{

    @Test
    public void calculateSizeTest(){
        final String dir = System.getProperty("user.dir");
        //TODO //дописать с чем сравнивать размер текущей директории
//        Assert.assertEquals(,FileUtils.calculateFolderSize(dir), 10e-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notExistedFolderTest(){
        final String dir = "NotExistedFolder";
        FileUtils.calculateFolderSize(dir);
    }

    @Test
    public void emptyFolderTest(){
        final String dir = System.getProperty("user.dir");
        File file = new File(dir + "\\TestFolder");
        file.mkdir();
        Assert.assertEquals(0, FileUtils.calculateFolderSize(file.getAbsolutePath()), 10e-5);
        file.delete();
    }
}

class FileUtils {
    private static List<File> files;
    public static long calculateFolderSize(String path) throws IllegalArgumentException {
        if(!path.matches("([a-zA-Z]:)?((\\\\(\\w ?)+)+)?")) throw new IllegalArgumentException("Неверный формат пути");
        File folder = new File(path);
        files = new ArrayList<>();
        getFiles(folder);
        return files.stream().mapToLong(File::length).sum();
    }

    private static void getFiles (File folder){
        if (!folder.isDirectory()) files.add(folder);
        else {
            try {
                Arrays.stream(folder.listFiles()).forEach(FileUtils::getFiles);
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
