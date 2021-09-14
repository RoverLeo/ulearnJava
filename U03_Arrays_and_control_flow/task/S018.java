package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S018 {
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
        char[][] chars = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == j || i + j == size - 1) chars[i][j] = 'X';
                else chars[i][j] = ' ';
            }
        }
        return chars;
    }

    public static String getStringArray(char[][] charArray){
        StringBuilder builder = new StringBuilder();
        for(char[] chars : charArray){
            for (char c : chars){
                builder.append(c);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    //#endregion Task
}
