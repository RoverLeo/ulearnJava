package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUlearn {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS018.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS018 {
    @Test
    public void getArrayTest() {
        char[][] chars = TwoDimensionalArray.getTwoDimensionalArray(5);
        Assert.assertArrayEquals(new char[][]{
                new char[]{'X', ' ', ' ', ' ', 'X'},
                new char[]{' ', 'X', ' ', 'X', ' '},
                new char[]{' ', ' ', 'X', ' ', ' '},
                new char[]{' ', 'X', ' ', 'X', ' '},
                new char[]{'X', ' ', ' ', ' ', 'X'},
        }, chars);
    }

    @Test
    public void getStringTest() {
        char[][] chars = new char[][]{
                new char[]{'X', ' ', ' ', ' ', 'X'},
                new char[]{' ', 'X', ' ', 'X', ' '},
                new char[]{' ', ' ', 'X', ' ', ' '},
                new char[]{' ', 'X', ' ', 'X', ' '},
                new char[]{'X', ' ', ' ', ' ', 'X'},
        };
        String str = "X   X\n" +
                " X X \n" +
                "  X  \n" +
                " X X \n" +
                "X   X\n";
        Assert.assertEquals(str, TwoDimensionalArray.getStringArray(chars));
    }
}

public class TwoDimensionalArray {
    //#region Task
    public static char[][] getTwoDimensionalArray(int size) {
        //TODO сгенерировать двумерный массив размера size
        return new char[0][0];
    }

    public static String getStringArray(char[][] charArray){
        //TODO вывести двумерный массив в формате строки с переносом строк
        return "";
    }
    //#endregion Task
}