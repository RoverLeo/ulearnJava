package U02_Base_Syntax_Types.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUlearn {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS020.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS020{

    @Test
    public void getNumberTest() {
        Assert.assertEquals(2, CalcUtils.getNumbers(114, 335));
    }

    @Test
    public void BigNumberTest() {
        Assert.assertEquals(3, CalcUtils.getNumbers(345345390, 12341209));
    }

    @Test
    public void NegativeNumberTest() {
        Assert.assertEquals(1, CalcUtils.getNumbers(-114, -937));
    }
}
public class CalcUtils{
    //#region Task
    public static int getNumbers(int a, int b) {
        //TODO необходимо вернуть количество четных цифр в сумме чисел a и b
        return 0;
    }
    //#endregion Task
}
