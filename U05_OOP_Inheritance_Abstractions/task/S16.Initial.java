package U05_OOP_Inheritance_Abstractions.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;

public class S16 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS16.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS16 {

    @Test
    public void createLionTest(){
        Lion animal = new Lion("Лев", 17, 136.2);
        Assert.assertEquals("Лев", animal.getName());
        Assert.assertEquals(17, animal.getAge());
        Assert.assertEquals(136.2, animal.getBodyLength(), 10e-10);
        Assert.assertEquals("Lion 'Лев', age= 17, bodyLength= 136.2", animal.toString());
    }

    @Test
    public void createMonkeyTest(){
        Monkey animal = new Monkey("Обезьянка", 5, "brown");
        Assert.assertEquals("Обезьянка", animal.getName());
        Assert.assertEquals(5, animal.getAge());
        Assert.assertEquals("brown", animal.getColor());
        Assert.assertEquals("Lion 'Обезьянка', age= 5, color= brown", animal.toString());
    }

    @Test
    public void createZooTest(){
        Zoo zoo = new Zoo();
        Monkey monkey = new Monkey("Обезьянка", 5, "brown");
        Lion lion = new Lion("Лев", 17, 136.2);
        zoo.add(monkey);
        zoo.add(lion);
        Assert.assertEquals("1 Lion 'Обезьянка', age= 5, color= brown\n" +
                "2 Lion 'Лев', age= 17, bodyLength= 136.2\n", zoo.getReport());
        Assert.assertEquals(2, zoo.getSize());
    }

}

//#region Task
public class Animal {
    //TODO реализуйте абстрактный класс Animal, с параметрами name и age
}

class Lion{
    //TODO реализуйте класс-наследник от Animal, с дополнительным параметром bodyLength
}


public class Monkey {
    //TODO реализуйте класс-наследник от Animal, с дополнительным параметром coatColor

}

public class Zoo {
    //TODO реализуйте класс Zoo, который будет хранить в себе животных, а также методы getSize и getReport
}
//#endregion Task
