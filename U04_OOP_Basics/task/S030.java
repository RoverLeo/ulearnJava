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
    private double cash = 0;

    public double getAmount() {
        return cash;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            cash += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (cash - amountToTake >= 0 && amountToTake > 0) {
            cash -= amountToTake;
        }
    }
}

public class PhysicalPerson extends Client {
}

public class LegalPerson extends Client {
    @Override
    public void take(double amountToTake) {
        super.take(amountToTake * 1.01);
    }
}

public class IndividualBusinessman extends Client {
    @Override
    public void put(double amountToPut) {
        if (amountToPut >= 1000) {
            super.put(amountToPut * 0.995);
        } else {
            super.put(amountToPut * 0.99);
        }
    }
}
//#endregion Task


