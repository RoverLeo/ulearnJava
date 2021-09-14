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
        Result result = junit.run(UnitTestS019.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS019{
    @Test
    public void divideTest() {
        Assert.assertEquals( 2.0, Calculator.calculate(6, 3, "/"), 10e-5);
    }

    @Test
    public void divideZeroTest() {
        Assert.assertEquals( 0, Calculator.calculate(6, 0, "/"),10e-5);
    }

    @Test
    public void multiplyTest() {
        Assert.assertEquals( 18, Calculator.calculate(6, 3, "*"),10e-5);
    }

    @Test
    public void plusTest() {
        Assert.assertEquals( 9, Calculator.calculate(6, 3, "+"),10e-5);
    }

    @Test
    public void minusTest() {
        Assert.assertEquals( 3, Calculator.calculate(6, 3, "-"),10e-5);
    }

    @Test
    public void WrongOperationTest() {
        Assert.assertEquals( 0, Calculator.calculate(6, 3, "abc"),10e-5);
    }

    @Test
    public void getMinimalByteTest() {
        Assert.assertEquals("Byte", Calculator.getMinimalType(Byte.MAX_VALUE));
    }

    @Test
    public void getMinimalShortTest() {
        Assert.assertEquals("Short", Calculator.getMinimalType(Short.MAX_VALUE));
    }

    @Test
    public void getMinimalIntTest() {
        Assert.assertEquals("Int", Calculator.getMinimalType(Integer.MAX_VALUE));
    }

    @Test
    public void getMinimalLongTest() {
        Assert.assertEquals("Long", Calculator.getMinimalType(Long.MAX_VALUE));
    }
}

class Calculator {
    //#region Task
    public static float calculate(int a, int b, double operation)
    {
        //TODO реализовать простой калькулятор
        return 0;
    }

    public static String getMinimalType(double input){
        //TODO определить самый наименьший целочисленный тип, к которому можно привести строку с числом
        return null;
    }
    //#endregion Task
}
