package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class JUlearn {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        Result result = junit.run(UnitTestS019.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            System.out.flush();
            System.setOut(old);
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS019{
    @Test
    public void addTest(){
        TodoList todoList = new TodoList();
        todoList.add("Дело 1");
        todoList.add("Дело 2");
        Assert.assertEquals(2, todoList.getTodos().size());
    }

    @Test
    public void addTestWithIndex(){
        TodoList todoList = new TodoList();
        todoList.add("Дело 2");
        todoList.add(0, "Дело 1");
        Assert.assertEquals(Arrays.asList("Дело 1", "Дело 2"), todoList.getTodos());
    }

    @Test
    public void deleteTest(){
        TodoList todoList = new TodoList();
        todoList.add("Дело 1");
        todoList.add("Дело 2");
        todoList.delete(0);
        Assert.assertEquals(Arrays.asList("Дело 2"), todoList.getTodos());
    }

    @Test
    public void editTest(){
        TodoList todoList = new TodoList();
        todoList.add("Дело 1");
        todoList.add("Дело 2");
        todoList.edit("Новое дело", 0);
        Assert.assertEquals(Arrays.asList("Новое дело", "Дело 2"), todoList.getTodos());
    }
}

//#region Task
public class TodoList {

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList
    }

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
    }

    public void delete(int index) {
//         TODO: удалить дело находящееся по переданному индексу,
//          проверьте возможность удаления дела
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return new ArrayList<>();
    }
}
//#endregion Task


