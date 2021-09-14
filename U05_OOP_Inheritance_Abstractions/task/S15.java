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

public class UnitTestS15 {

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

    /**
     * Возвращает продолжительность текущего интервала, пересчитанного в миллисекундах.
     *
     * @return количество миллисекунд в текущем интервале
     */
    long toMillis();

    /**
     * Возвращает продолжительность текущего интервала, пересчитанного в секундах.
     * При необходимости округлять по обычным правилам округления (число, меньшее 0.5 переходит в 0, большее или равное - в 1)
     *
     * @return количество секунд в текущем интервале
     */
    long toSeconds();

    /**
     * Возвращает продолжительность текущего интервала, пересчитанного в минутах.
     * При необходимости округлять по обычным правилам округления (число, меньшее 0.5 переходит в 0, большее или равное - в 1)
     *
     * @return количество минут в текущем интервале
     */
    long toMinutes();
    long toHours();
}

public class TimeUnitUtils {

    public static Milliseconds toMillis(TimeUnit timeUnit) {
        return new Milliseconds(timeUnit.toMillis());
    }

    public static Seconds toSeconds(TimeUnit timeUnit) {
        return new Seconds(timeUnit.toSeconds());
    }

    public static Hours toHours(TimeUnit timeUnit) {
        return new Hours(timeUnit.toHours());
    }
}

public class Seconds implements TimeUnit {

    private final long amount;

    public Seconds(long amount) {
        this.amount = amount;
    }

    public long toMillis() {
        return amount * 1000;
    }

    public long toSeconds() {
        return amount;
    }

    public long toMinutes() {
        return Math.round((double) amount / 60);
    }

    public long toHours() {
        return Math.round((double) amount / (60 * 60));
    }
}

public class Minutes implements TimeUnit {
    private final long amount;

    public Minutes(long amount) {
        this.amount = amount;
    }

    public long toMillis() {
        return amount * 60 * 1000;
    }

    public long toSeconds() {
        return amount * 60;
    }

    public long toMinutes() {
        return amount;
    }

    public long toHours() {
        return Math.round((double) amount / 60);
    }
}

public class Milliseconds implements TimeUnit {

    private final long amount;

    public Milliseconds(long amount) {
        this.amount = amount;
    }

    public long toMillis() {
        return amount;
    }

    public long toSeconds() {
        return amount / 1000;
    }

    public long toMinutes() {
        return Math.round((double) amount / (1000 * 60));
    }

    public long toHours() {
        return Math.round((double) amount / (1000 * 60 * 60) );
    }
}

public class Hours implements TimeUnit {

    private final long amount;

    public Hours(long amount) {
        this.amount = amount;
    }

    public long toMillis() {
        return amount * 60 * 60 * 1000;
    }

    public long toSeconds() {
        return amount * 60 * 60;
    }

    public long toMinutes() {
        return amount * 60;
    }

    public long toHours() {
        return amount;
    }
}
//#endregion Task
