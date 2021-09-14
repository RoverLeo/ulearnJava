package U05_OOP_Inheritance_Abstractions.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S15 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS15.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS15{

        /**
         * Проверяет ожидаемое значение интервала в различных единицах с реальными
         * @param timeUnit временной интервал
         * @param expectedMillis ожидаемое количество миллисекунд
         * @param expectedSeconds ожидаемое количество секунд
         * @param expectedMinutes ожидаемое количество минут
         */
        private void testTimeUnit(TimeUnit timeUnit, long expectedMillis, long expectedSeconds, long expectedMinutes) {
            long actualMillis = timeUnit.toMillis();
            long actualSeconds = timeUnit.toSeconds();
            long actualMinutes = timeUnit.toMinutes();
            Assert.assertEquals("Количество миллисекунд не соответствует ожидаемому", expectedMillis, actualMillis);
            Assert.assertEquals("Количество секунд не соответствует ожидаемому", expectedSeconds, actualSeconds);
            Assert.assertEquals("Количество минут не соответствует ожидаемому", expectedMinutes, actualMinutes);
        }

        @Test
        public void testMilliseconds() {
            testTimeUnit(new Milliseconds(1000), 1000, 1, 0);
        }

        @Test
        public void testMilliseconds3() {
            testTimeUnit(new Milliseconds(30000), 30000, 30, 1);
        }

        @Test
        public void testMilliseconds4() {
            testTimeUnit(new Milliseconds(1000000), 1000000, 1000, 17);
        }

        @Test
        public void testSeconds() {
            testTimeUnit(new Seconds(60), 60000, 60, 1);
        }

        @Test
        public void testSeconds2() {
            testTimeUnit(new Seconds(29), 29000, 29, 0);
        }

        @Test
        public void testSeconds3() {
            testTimeUnit(new Seconds(30), 30000, 30, 1);
        }

        @Test
        public void testMinutes() {
            testTimeUnit(new Minutes(10), 600000, 600, 10);
        }

        @Test
        public void testMinutes2() {
            testTimeUnit(new Minutes(70), 4200000, 4200, 70);
        }


        @Test
        public void testUtils2() {
            Seconds seconds = TimeUnitUtils.toSeconds(new Milliseconds(1499));
            testTimeUnit(seconds, 1000, 1, 0);
        }

        @Test
        public void testUtils3() {
            Milliseconds millis = TimeUnitUtils.toMillis(new Seconds(29));
            testTimeUnit(millis, 29000, 29, 0);
        }

        @Test
        public void testUtils4() {
            Milliseconds millis = TimeUnitUtils.toMillis(new Seconds(30));
            testTimeUnit(millis, 30000, 30, 1);
        }
}

/**
 * Интерфейс для интервалов времени в определенных единицах
 */
//#region Task
public interface TimeUnit {
    //TODO реализуйте интерфейс TimeUnit
}

public class TimeUnitUtils {
    //TODO реализуйте методы toMillis(), toSeconds() и toHours()
}

public class Seconds{
    //TODO реализуйте класс Seconds, который наследует класс TimeUnit
}

public class Minutes{
    //TODO реализуйте класс Minutes, который наследует класс TimeUnit

}

public class Milliseconds{
    //TODO реализуйте класс Milliseconds, который наследует класс TimeUnit
}

public class Hours{
    //TODO реализуйте класс Hours, который наследует класс TimeUnit
}
//#endregion Task
