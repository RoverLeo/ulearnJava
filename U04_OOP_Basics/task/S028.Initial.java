package U04_OOP_Basics.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class JUlearn {

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTest028.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

//#region Task
public class School {

    public void PupilOut(Pupil pupil) {
        //ученик вышел из школы
    }

    public void PupilIn(Pupil pupil) {
        //ученик в школе
    }

    public void TeacherOut(Teacher teacher) {
        //учитель вышел из школы
    }

    public void TeacherIn(Teacher teacher) {
        //учитель в школе
    }
}

public class Pupil {
    //напишите класс Pupil с конструктором, геттерами и сеттерами и переменными name, surname, year

}

public class Teacher {
    //напишите класс Teacher с конструктором, геттером, сеттером и методом toString
}
//#endregion Task

public class UnitTest028 {

    @Test
    public void nameTest() {
        Pupil newpupil = new Pupil("Ivan", "Ivanov", 8);
        Assert.assertEquals("Ivan", newpupil.getName());
    }

    @Test
    public void surnameTest() {
        Pupil newpupil = new Pupil("Ivan", "Ivanov", 8);
        Assert.assertEquals("Ivanov", newpupil.getSurname());
    }

    @Test
    public void yearTest() {
        Pupil newpupil = new Pupil("Ivan", "Ivanov", 8);
        Assert.assertEquals(8, newpupil.getYear());
    }

    @Test
    public void toStringTest() {
        Pupil newpupil = new Pupil("Ivan", "Ivanov", 8);
        Assert.assertEquals("Ivan Ivanov", newpupil.toString());
    }

    @Test
    public void pupilOut() {
        School testSchool = new School();
        Pupil newPupil = new Pupil("Ivan", "Ivanov", 6);
        testSchool.pupils.put(newPupil, Boolean.TRUE);
        testSchool.PupilOut(newPupil);
        Assert.assertFalse(testSchool.pupils.get(newPupil));
    }

    @Test
    public void pupilIn() {
        School testSchool = new School();
        Pupil newPupil = new Pupil("Ivan", "Ivanov", 6);
        testSchool.pupils.put(newPupil, Boolean.FALSE);
        testSchool.PupilIn(newPupil);
        Assert.assertTrue(testSchool.pupils.get(newPupil));
    }

    @Test
    public void teacherOut() {
        School testSchool = new School();
        Teacher newTeacher = new Teacher("Ivan", "Ivanov", "Math",26);
        testSchool.teachers.put(newTeacher, Boolean.TRUE);
        testSchool.TeacherOut(newTeacher);
        Assert.assertFalse(testSchool.teachers.get(newTeacher));
    }

    @Test
    public void teacherIn() {
        School testSchool = new School();
        Teacher newTeacher = new Teacher("Ivan", "Ivanov", "Math",26);
        testSchool.teachers.put(newTeacher, Boolean.FALSE);
        testSchool.TeacherIn(newTeacher);
        Assert.assertTrue(testSchool.teachers.get(newTeacher));
    }

    @Test
    public void testAttendance() {
        School testSchool = new School();
        Teacher newTeacher1 = new Teacher("Ivan", "Ivanov", "Math",26);
        Teacher newTeacher2 = new Teacher("Alexey", "Smirnov", "Russian",21);
        Pupil newPupil1 = new Pupil("Ivan", "Sidorov", 5);
        Pupil newPupil2 = new Pupil("Maria", "Kozlova", 8);
        testSchool.teachers.put(newTeacher1, Boolean.TRUE);
        testSchool.teachers.put(newTeacher2, Boolean.FALSE);
        testSchool.pupils.put(newPupil1, Boolean.TRUE);
        testSchool.pupils.put(newPupil2, Boolean.FALSE);
        Assert.assertEquals("[Ivan Sidorov, Ivan Ivanov]", testSchool.getPeoplesInSchool());
    }

    @Test
    public void testToString() {
        Teacher newteacher = new Teacher("Ivan", "Ivanov", "Math",24);
        Assert.assertEquals("Ivan Ivanov", newteacher.toString());
    }
}
