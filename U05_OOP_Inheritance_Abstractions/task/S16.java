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
public abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Lion " +
                "'" + name + '\'' +
                ", age= " + age;
    }
}

public class Lion extends Animal {
    public double bodyLength;

    public Lion(String name, int age, double bodyLength) {
        super(name, age);
        this.bodyLength = bodyLength;
    }

    public double getBodyLength() {
        return bodyLength;
    }

    @Override
    public String toString() {
        return super.toString() + ", bodyLength= " + bodyLength;
    }
}


public class Monkey extends Animal {
    public String color;

    public Monkey(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString() + ", color= " + color;
    }
}

public class Zoo {
    private ArrayList<Animal> animals = new ArrayList<>();

    public void add(Animal animal){
        animals.add(animal);
    }

    public int getSize() {
        return animals.size();
    }

    public String getReport() {
        int counter = 1;
        StringBuilder result = new StringBuilder();
        for (Animal animal : animals) {
            result.append(String.format("%d %s\n", counter, animal.toString()));
            counter++;
        }
        return result.toString();
    }
}
//#endregion Task
