package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private CustomerStorage storage;

    @Test(expected = Throwable.class)
    public void moreThenFourElementsInputString() {
        final String input = "Василий Петров vasily.petrov@gmail.com +79215637722 5слово";

        storage = new CustomerStorage();
        storage.addCustomer(input);
    }

    @Test
    public void lessThanFourElementsInputString() {
        final String input = "Василий Петров vasily.petrov@gmail.com";

        storage = new CustomerStorage();
        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "Не выброшено исключение при количестве элементов в строке меньше 4");
    }

    @Test
    @DisplayName("Неверный формат email")
    public void wrongEmailFormatWithoutAt() {
        final String wrongEmail = "thisIsNotAnEmail";
        final String input = "Василий Петров " + wrongEmail + " +79215637722";

        storage = new CustomerStorage();
        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "Не выброшено исключение при неверном формате email -> " + wrongEmail);
    }

    @Test
    @DisplayName("Неверный формат номера")
    public void wrongPhoneFormatWithoutDigits() {
        final String wrongPhoneNumber = "+thisIsNotANumber";
        final String input = "Василий Петров hello@skillbox.ru " + wrongPhoneNumber;

        storage = new CustomerStorage();
        assertThrows(Throwable.class, () -> storage.addCustomer(input),
                "Не выброшено исключение при неверном формате номера -> " + wrongPhoneNumber);
    }

    @Test
    @DisplayName("Тест добавления корректных данных Customer")
    public void insertCorrectData() {
        final String name = "Василий Петров";
        final String email = "hello@skillbox.ru";
        final String phone = "+79991234567";
        final String input = String.join(" ", name, email, phone);

        storage = new CustomerStorage();
        storage.addCustomer(input);
        assertEquals(1, storage.getCount());

        Customer customer = storage.getCustomer(name);
        assertEquals(name, customer.getName());
        assertEquals(email, customer.getEmail());
        assertEquals(phone, customer.getPhone());
    }
}

//#region Task
public class Customer {
    private final String name;
    private final String phone;
    private final String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return name + " - " + email + " - " + phone;
    }
}

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals("add")) {
                executor.addCustomer(tokens[1]);
            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                try {
                    executor.removeCustomer(tokens[1]);
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    ex.printStackTrace();
                }
            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                System.out.println(helpText);
            } else {
                System.out.println(COMMAND_ERROR);
            }
        }
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) throw new IllegalArgumentException("Неверный формат данных.");
        if (!components[INDEX_EMAIL].matches(".+@.+")) throw new IllegalArgumentException("Неверный формат e-mail");
        if (!components[INDEX_PHONE].matches("\\+?7?8?\\d{10}")) throw new IllegalArgumentException("Неверный формат номера");
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
//#endregion Task
