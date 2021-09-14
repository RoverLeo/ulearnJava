package U05_OOP_Inheritance_Abstractions.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;

public class S18 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS18.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

class UnitTestS18{

    @Test
    void debugTest() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        Assert.assertEquals("[DEBUG] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Ok>", log.debug("Ok"));
    }

    @Test
    void infoTest() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        Assert.assertEquals("[INFO] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Ok>", log.info("Ok"));
    }



    @Test
    void warningTest() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        Assert.assertEquals("[WARNING] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Ok>", log.warning("Ok"));
    }

    @Test
    void errorTest() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        Assert.assertEquals("[ERROR] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Ok>", log.error("Ok"));
    }

    @Test
    void testToString() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        log.message = "Error";
        Assert.assertEquals("[<" + Level.WARNING.toString() + ">] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Error>",
                log.toString());
    }

    @Test
    void logTest() {
        Logger log = new Logger();
        log.level = Level.WARNING;
        log.setCalendar(2020, 2, 1, 12, 15, 0);
        log.name = "logger1";
        log.message = "Error";
        log.log(Level.FINE, "OK");
        Assert.assertEquals(log.level, Level.FINE);
        Assert.assertEquals(log.message, "OK");
    }

    @Test
    void addLogger() {
        logManager lManager = new logManager();
        Logger logger = new Logger();
        lManager.AddLogger(logger);
        Assert.assertEquals(1, lManager.loggers.size());
    }

    @Test
    void getLogger() throws Exception {
        logManager lManager = new logManager();
        Logger logger = new Logger();
        logger.name = "logger1";
        lManager.AddLogger(logger);
        Assert.assertEquals(logger, lManager.GetLogger("logger1"));
    }

    @Test
    void printLoggers() {
        logManager lManager = new logManager();
        Logger log1 = new Logger();
        log1.level = Level.WARNING;
        log1.setCalendar(2020, 2, 1, 12, 15, 0);
        log1.name = "logger1";
        log1.message = "Error";
        Logger log2 = new Logger();
        log2.level = Level.FINE;
        log2.setCalendar(2020, 2, 2, 20, 7, 0);
        log2.name = "logger2";
        log2.message = "Ok";
        lManager.AddLogger(log1);
        lManager.AddLogger(log2);
        Assert.assertEquals("[<WARNING>] <Sat Feb 01 12:15:00 MSK 2020> <logger1> - <Error>" + "\n" +
                        "[<FINE>] <Sun Feb 02 20:07:00 MSK 2020> <logger2> - <Ok>",
                lManager.printLoggers());
    }
}

class Logger {
    public Level level;
    public Calendar calendar;
    public String name;
    public String message;

    public String debug(String message)
    {
        return String.format("[DEBUG] <%s> <%s> <%s>", calendar.getTime(), name, message);

    }
    public String info(String message)
    {
        return String.format("[INFO] <%s> <%s> <%s>", calendar.getTime(), name, message);
    }
    public String warning(String message)
    {
        return String.format("[WARNING] <%s> <%s> <%s>", calendar.getTime(), name, message);
    }
    public String error(String message)
    {
        return String.format("[ERROR] <%s> <%s> <%s>", calendar.getTime(), name, message);
    }

    public String toString()
    {
        return String.format("[%s] <%s> <%s> <%s>", level, calendar.getTime(), name, message);
    }

    public void log(Level level, String message)
    {
        this.level = level;
        this.message = message;
    }

    public void setCalendar(int year, int month, int day, int hours, int minutes, int seconds) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        this.calendar = calendar;
    }
}

class logManager {
    ArrayList<Logger> loggers = new ArrayList<>();

    public void AddLogger(Logger logger)
    {
        loggers.add(logger);
    }

    public Logger GetLogger(String name) {
        for (Logger log: loggers)
        {
            if (log.name.equals(name))
                return log;
        }
        return new Logger();
    }

    public String printLoggers()
    {
        StringBuilder result = new StringBuilder();
        for (Logger log: loggers)
        {
            result.append(log.toString());
            result.append("\n");
        }
        return result.toString();
    }
}

