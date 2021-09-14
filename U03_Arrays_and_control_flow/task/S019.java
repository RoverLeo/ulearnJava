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
import java.util.Scanner;

public class S019 {
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
    private static TodoList todoList = new TodoList();
    ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true){
            System.out.println("Введите команду:");
            String string = new Scanner(System.in).nextLine();
            String[] s = string.split(" ");
            switch (s[0]){
                case "ADD":{
                    try {
                        int index = Integer.parseInt(s[1]);
                        todoList.add(index, string.substring(string.indexOf(' ', 4)));
                        break;
                    }
                    catch (Exception e){
                        todoList.add(string.substring(string.indexOf(' ') + 1));
                        break;
                    }

                }
                case "LIST":{
                    int count = 0;
                    for (String str : todoList.getTodos()){
                        System.out.println(String.format("%d - %s", count, str));
                        count++;
                    }
                }
                case "EDIT":{
                    try {
                        int index = Integer.parseInt(s[1]);
                        todoList.edit(string.substring(string.indexOf(' ', 5) + 1), index);
                        break;
                    }
                    catch (Exception e){
                        break;
                    }
                }
                case "DELETE": {
                    try {
                        int index = Integer.parseInt(s[1]);
                        todoList.delete(index);
                        break;
                    }
                    catch (Exception e){
                        break;
                    }
                }

                default:{
                    break;
                }
            }
        }
    }

    public void add(String todo) {
        list.add(todo);
        System.out.println("Добавлено дело \"" + todo + "\"");
    }

    public void add(int index, String todo) {
        if (index < list.size() && index >= 0){
            list.add(index, todo);
        }
        else if (index > list.size()){
            list.add(todo);
        }
        System.out.println("Добавлено дело \"" + todo + "\"");
    }

    public void edit(String todo, int index) {
        if (index < list.size() && index >= 0){
            System.out.println("Дело \"" + list.get(index) + "\" было заменено на дело \"" + todo + "\"");
            list.remove(index);
            list.add(index, todo);
        }
        else System.out.println("Изменение несущетсвующего дела");
    }

    public void delete(int index) {
        if (index < list.size() && index >= 0){
            System.out.println("Дело \"" + list.get(index) + "\" было удалено.");
            list.remove(index);
        }
        else System.out.println("Дело с таким номером не существует");
    }

    public ArrayList<String> getTodos() {
        return list;
    }
}
//#endregion Task

