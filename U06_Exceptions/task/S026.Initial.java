package U06_Exceptions.task;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
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
        Result result = junit.run(UnitTestS026.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS026{
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
public class Main{
    public static void main(String[] args) {
        //TODO напишите консольное приложение для взаимодействия с продавцами
    }
}
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
    //TODO реализуйте методы получения продавца, удаления, и получения количества продавцов

    public void addCustomer(String data) {
        //TODO реализуйте метод addCustomer() и отловите все возможные ошибки
    }
}
//#endregion Task
