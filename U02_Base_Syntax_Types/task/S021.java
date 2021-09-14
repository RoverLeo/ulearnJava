package U02_Base_Syntax_Types.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S021 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS021.class); // им€ класса, который хотим тестировать
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
        Assert.assertEquals("!абвгддгвба!", StringUtils.getRevertString("!аЅвгƒƒ√вбј!"));
    }

    @Test
    public void FirstLetterUpperCaseTest() {
        Assert.assertEquals("јбвггвба", StringUtils.getRevertString("аЅвг√вбј"));
    }

    @Test
    public void NonAlphabeticTest() {
        Assert.assertEquals("#@%*$#(", StringUtils.getRevertString("(#$*%@#"));
    }

    @Test
    public void FullWorkTest() {
        Assert.assertEquals("Ќу как же писать этот код!", StringUtils.getRevertString("!дќк “о“э ь“јсиѕ ≈ж кјк ”Ќ"));
    }
}

public class StringUtils{
    //#region Task
    public static String getRevertString(String str)
    {
        StringBuilder result = new StringBuilder();
        for (char i: str.toCharArray()) {
            if (str.indexOf(i) == str.length() - 1)
                result.append(Character.toUpperCase(i));
            else if (Character.isAlphabetic(i))
                result.append(Character.toLowerCase(i));
            else
                result.append(i);
        }
        return result.reverse().toString();
    }
    //#endregion Task
}