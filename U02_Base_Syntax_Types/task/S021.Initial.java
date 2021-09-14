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
        Result result = junit.run(UnitTestS021.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS021{
    @Test
    public void CaseTest() {
        Assert.assertEquals("!абвгддгвба!", StringUtils.getRevertString("!аБвгДДГвбА!"));
    }

    @Test
    public void FirstLetterUpperCaseTest() {
        Assert.assertEquals("Абвггвба", StringUtils.getRevertString("аБвгГвбА"));
    }

    @Test
    public void NonAlphabeticTest() {
        Assert.assertEquals("#@%*$#(", StringUtils.getRevertString("(#$*%@#"));
    }

    @Test
    public void FullWorkTest() {
        Assert.assertEquals("Ну как же писать этот код!", StringUtils.getRevertString("!дОк ТоТэ ьТАсиП Еж кАк УН"));
    }
}

public class StringUtils{
    //#region Task
    public static String getRevertString(String str)
    {
        //TODO реализовать метод
        return "";
    }
    //#endregion Task
}