package U04_OOP_Basics.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S030 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS030.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS030{
    @Test
    public void testPutIndividualBusinessman(){
        Client physicalPerson = new PhysicalPerson();
        physicalPerson.put(1000);
        Assert.assertEquals(1000, physicalPerson.getAmount(), 10e-5);
    }

    @Test
    public void testTakeIndividualBusinessman(){
        Client physicalPerson = new PhysicalPerson();
        physicalPerson.put(1000);
        physicalPerson.take(1000);
        Assert.assertEquals(0, physicalPerson.getAmount(), 10e-5);
    }

    @Test
    public void testTakeWrongNumberInBusinessman(){
        Client physicalPerson = new PhysicalPerson();
        physicalPerson.put(1000);
        physicalPerson.take(-1000);
        Assert.assertEquals(1000, physicalPerson.getAmount(), 10e-5);
    }

    @Test
    public void testTakeLegalPerson(){
        Client legalPerson = new LegalPerson();
        legalPerson.put(1000);
        legalPerson.take(500);
        Assert.assertEquals(495, legalPerson.getAmount(), 10e-5);
    }

    @Test
    public void testTakeIndBusinessman(){
        Client businessman = new IndividualBusinessman();
        businessman.put(500);
        businessman.take(100);
        Assert.assertEquals(395, businessman.getAmount(), 10e-5);
        businessman.put(2000);
        Assert.assertEquals(2385, businessman.getAmount(), 10e-5);
    }
}

//#region Task
public abstract class Client {
    /*TODO написать классы Client, IndividualBusinessman, PhysicalPerson и LegalPerson
        с методами put(), take(), getAmount()
    */
}
//#endregion Task


