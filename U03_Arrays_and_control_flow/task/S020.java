package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class S020 {
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
//        phoneBook = new PhoneBook();
//        phoneBook.addContact("89223365452", "Матвей");
//        phoneBook.addContact("89213154651", "Саша");
//        phoneBook.addContact("89213154123", "Максим");
//        phoneBook.addContact("89213154621", "Стас");
//        Assert.assertEquals(4, phoneBook.getAllContacts().size());
//    }

    @Test
    public void isCorrectTest(){
        phoneBook = new PhoneBook();
        Assert.assertTrue(phoneBook.isCorrect("Никита", "89223839245"));
        Assert.assertFalse(phoneBook.isCorrect("asdasdasd", "sdasd48943532"));
    }

}

//#region Task
public class Main{

    public static void main(String[] args) {
        Scanner scanner = new java.util.Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        while (true){
            System.out.println("Введите номер, имя или команду: ");
            String input = scanner.nextLine();
            if (input.equals("LIST")){
                for(String contact: phoneBook.getAllContacts()){
                    System.out.println(contact);
                }
                continue;
            }
            if (input.matches("[а-яА-Я|A-Za-z]+")){
                String name = input;
                if (phoneBook.checkContacts(name)){
                    System.out.println(phoneBook.getPhonesByName(name));
                }
                else{
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента \"" + name +"\": ");
                    String number = scanner.nextLine();
                    while (!number.matches("\\+?\\d{11}")){
                        System.out.println("Введен некорректный номер. Попробуйте снова в формате: +7XXXXXXXXXX");
                        number = scanner.nextLine();
                    }
                    phoneBook.addContact(number, name);
                    System.out.println(String.format("Контакт \"%s\" успешно добавлен.", name));
                    continue;
                }
            }
            if (input.matches("\\+?\\d{11}")){
                try {
                    System.out.println(phoneBook.getNameByPhone(input));
                    continue;
                }
                catch (Exception e){
                    String number = input;
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println(String.format("Введите имя абонента для номера \"%s\": ", number));
                    String name = scanner.nextLine();
                    while (!name.matches("[а-яА-Я|A-Za-z]+")){
                        System.out.println("Введено некоррекное имя.");
                        name = scanner.nextLine();
                    }
                    phoneBook.addContact(number, name);
                    System.out.println(String.format("Контакт \"%s\" успешно добавлен.", name));
                    continue;
                }

            }
            if (input.matches("[а-яА-Я|A-Za-z]+ \\+?\\d{11}")){
                String[] strings = input.split(" ");
                phoneBook.addContact(strings[1], strings[0]);
                System.out.println(String.format("Контакт \"%s\" успешно добавлен.", strings[0]));
            }
        }
    }
}

public class PhoneBook {
    private TreeMap<String, Set<String>> contacts = new TreeMap<>();

    public void addContact(String phone, String name) {
        if (!isCorrect(name, phone)) return;
        if (checkContacts(phone)) {
            String key = getNameByPhone(phone).split(" ")[0];
            Set<String> numbers = contacts.get(key);
            contacts.remove(key);
            contacts.put(name, numbers);
        }
        if (contacts.containsKey(name)) {
            contacts.get(name).add(phone);
        } else {
            Set<String> phones = new HashSet<>();
            phones.add(phone);
            contacts.put(name, phones);
        }
    }

    public String getNameByPhone(String phone) {
        String result = "";
        for (Map.Entry<String, Set<String>> contact : contacts.entrySet()) {
            for (String number : contacts.get(contact.getKey())) {
                if (number.equals(phone)) {
                    result = contact.getKey();
                    break;
                }
            }
        }
        return result;
    }

    public Set<String> getPhonesByName(String name) {
        return contacts.get(name);
    }

    public StringBuilder getNumbersToString(Set<String> numbers) {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            sb.append(number).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb;
    }

    public Set<String> getAllContacts() {
        Set<String> result = new TreeSet<>();
        for (Map.Entry<String, Set<String>> contact : contacts.entrySet()) {
            StringBuilder sb = getNumbersToString(contact.getValue());
            result.add(String.format("%s - %s", contact.getKey(), sb.toString()));
        }
        return result;
    }

    public boolean checkContacts(String name) {
        for (String contact : this.getAllContacts()) {
            if (contact.contains(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(String name, String number) {
        return name.matches("[а-яА-Я|A-Za-z]+") && number.matches("\\+?7|8?\\d{10}");
    }
}
//#endregion Task




