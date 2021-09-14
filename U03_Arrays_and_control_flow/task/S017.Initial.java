package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUlearn {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS017.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

public class UnitTestS017 {
    @Test
    public void getReportTest() {
        float[] temperatures = new float[]{36.6f, 32.3f, 37f, 34.5f, 36f};
        Assert.assertEquals("Температуры пациентов: 36,6 32,3 37,0 34,5 36,0\n" +
                "Средняя температура: 35,28\n" +
                "Количество здоровых: 1", Hospital.getReport(temperatures));
    }

    @Test
    public void countTest() {
        float[] temperatures = Hospital.generatePatientsTemperatures(10);
        Assert.assertEquals(10, temperatures.length);
    }

    @Test
    public void generateTest() {
        float[] temperatures = Hospital.generatePatientsTemperatures(100);
        for (float temperature : temperatures) {
            Assert.assertTrue(temperature > 32 && temperature < 40);
        }
    }
}

public class Hospital {
    //#region Task
    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO реализовать генерацию массива температур
        return new float[0];
    }

    public static String getReport(float[] temperatureData) {
        /*TODO Формат вывода:
           Температуры пациентов: 37,5 36,9 38,2 33,5 32,2
           Средняя температура: 35,67
           Количество здоровых: 1
         */
        return "";
    }
    //#endregion Task
}