package U03_Arrays_and_control_flow.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class S017 {
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

public class UnitTestS017{
    @Test
    public void getReportTest(){
        float[] temperatures = new float[]{36.6f, 32.3f, 37f, 34.5f, 36f};
        Assert.assertEquals("""
                Температуры пациентов: 36,6 32,3 37,0 34,5 36,0
                Средняя температура: 35,28
                Количество здоровых: 1""",Hospital.getReport(temperatures));
    }

    @Test
    public void countTest(){
        float[] temperatures = Hospital.generatePatientsTemperatures(10);
        Assert.assertEquals(10, temperatures.length);
    }

    @Test
    public void generateTest(){
        float[] temperatures = Hospital.generatePatientsTemperatures(100);
        for(float temperature : temperatures){
            Assert.assertTrue(temperature > 32 && temperature < 40);
        }
    }
}

class Hospital{
    //#region Task
    public static float[] generatePatientsTemperatures(int patientsCount) {
        float[] floats = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            floats[i] = (float) (32 + Math.random() * 8);
        }
        return floats;
    }

    public static String getReport(float[] temperatureData) {
        StringBuilder builder = new StringBuilder();
        float sum = 0;
        int count = 0;
        for (int i = 0; i < temperatureData.length; i++) {
            if (temperatureData[i] > 36 && temperatureData[i] < 37) count++;
            sum += temperatureData[i];
            builder.append(String.format("%.1f", temperatureData[i]));
            if (i != temperatureData.length - 1) builder.append(" ");
        }

        String report =
                "Температуры пациентов: " + builder.toString() +
                        "\nСредняя температура: " + String.format("%.2f", (sum / temperatureData.length)) +
                        "\nКоличество здоровых: " + count;
        return report;

    }
    //#endregion Task
}