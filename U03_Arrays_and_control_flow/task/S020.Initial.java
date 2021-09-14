package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
    private PhoneBook phoneBook;

    @Test
    public void addContact(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89224651233", "Алекс");
        phoneBook.addContact("89456131201", "Николай");
        Assert.assertEquals(2, phoneBook.getAllContacts().size());
    }

    @Test
    public void inCorrectNumber(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89224651233", "Алекс");
        phoneBook.addContact("dasdadsadas", "Николай");
        Assert.assertEquals(1, phoneBook.getAllContacts().size());
    }

    @Test
    public void getNameByPhoneTest(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89224651233", "Алекс");
        phoneBook.addContact("89456131201", "Николай");
        Assert.assertEquals("Алекс", phoneBook.getNameByPhone("89224651233"));
    }

    @Test
    public void getPhonesByName(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89224651233", "Алекс");
        phoneBook.addContact("89452369710", "Алекс");
        Set<String> contacts = new HashSet<>();
        contacts.add("89224651233");
        contacts.add("89452369710");
        Assert.assertEquals(contacts, phoneBook.getPhonesByName("Алекс"));
    }

    @Test
    public void getNumbersToStringTest(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89223365452", "Матвей");
        phoneBook.addContact("89223365423", "Матвей");
        Assert.assertEquals("89223365452, 89223365423", phoneBook.getNumbersToString(phoneBook.getPhonesByName("Матвей")).toString());
    }

    @Test
    public void checkContactsTest(){
        phoneBook = new PhoneBook();
        phoneBook.addContact("89223365423", "Никита");
        assertTrue(phoneBook.checkContacts("Никита"));
        assertFalse(phoneBook.checkContacts("Игорь"));
    }

//    @Test
//    public void getAllContactsTest(){
//        PhoneBook phoneBook = new PhoneBook();
//        phoneBook.addContact("89223365452", "Матвей");
//        phoneBook.addContact("89213154651", "Саша");
//        phoneBook.addContact("89213154123", "Максим");
//        phoneBook.addContact("89213154621", "Стас");
//        Assert.assertEquals(4, phoneBook.getAllContacts().size());
//    }

    @Test
    public void isCorrectTest(){
        PhoneBook phoneBook = new PhoneBook();
        Assert.assertTrue(phoneBook.isCorrect("Никита", "89223839245"));
        Assert.assertFalse(phoneBook.isCorrect("asdasdasd", "sdasd48943532"));
    }
}

//#region Task
public class Main{
    public static void main(String[] args) {
        //TODO напишите консольное приложение для взаимодействия с телефонной книгой
    }
}

public class PhoneBook {

    public void addContact(String phone, String name) {
        //TODO проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getNameByPhone(String phone) {
        //TODO формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        //TODO по имени вернуть список номеров
        return null;
    }

    public StringBuilder getNumbersToString(Set<String> numbers) {
        //TODO вернуть номера в формате строки <номер>, <номер>.
        return null;
    }

    public Set<String> getAllContacts() {
        //TODO вернуть все контакты в формате <имя> — <номер>, <номер> и тд
        return null;
    }

    public boolean checkContacts(String name) {
        //TODO проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        return false;
    }

    public boolean isCorrect(String name, String phone){
        //TODO проверьте корректность номера
        return true;
    }
}
//#endregion Task
