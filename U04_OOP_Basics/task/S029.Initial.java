package U04_OOP_Basics.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.Arrays;

public class S029 {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS029.class); // имя класса, который хотим тестировать
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(new RealSystem());
            listener.testRunFinished(result);
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}

class UnitTestS029 {

    //PolygonalLineTest
    @Test
    void getLength() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        Point p3 = new Point(3,0);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        Line line3 = new Line(p3, p1);
        PolygonalLine p_line = new PolygonalLine(Arrays.asList(line1, line2, line3));
        Assert.assertEquals(12, p_line.getLength(), 10e-5);
    }

    //PointTest
    @Test
    void getXTest() {
        Point p = new Point(1,2);
        Assert.assertEquals(1, p.getX());
    }

    @Test
    void getYTest() {
        Point p = new Point(1,2);
        Assert.assertEquals(2, p.getY());
    }

    @Test
    void getDistanceTest_1() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(5,0);
        Assert.assertEquals(5, p1.getDistance(p2), 10e-5);
    }

    @Test
    void getDistanceTest_2() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        Assert.assertEquals(5, p1.getDistance(p2), 10e-5);
    }

    @Test
    void toStringTest() {
        Point p1 = new Point(3,1);
        Assert.assertEquals("[ 3, 1 ]", p1.toString());
    }

    @Test
    void hasPointTest(){
        Line line = new Line(new Point(0, 0), new Point(100, 100));
        Point p = new Point(50, 50);
        Assert.assertTrue(line.hasPoint(p));
    }

    @Test
    void getLengthTest() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        Point p3 = new Point(3,0);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        Line line3 = new Line(p3, p1);
        PolygonalLine p_line = new PolygonalLine(Arrays.asList(line1, line2, line3));
        Assert.assertEquals(12, p_line.getLength(), 10e-5);
    }

    @Test
    void addPointTest(){
        Line line1 = new Line(new Point(0, 0), new Point(10, 0));
        Line line2 = new Line(new Point(10, 0), new Point(10, 10));
        PolygonalLine line = new PolygonalLine(new ArrayList<Line>(Arrays.asList(line1, line2)));
        line.addPoint(new Point(20, 10));
        Assert.assertEquals(30, line.getLength(),10e-5);
    }
}

//#region Task
public class Line {
    //реализуйте получение стартовой точки, конечной, toString, GetLength и hasPoint
}

public class Point {
    //реализуйте геттеры, сеттеры и конструктор

    public double getDistance(Point p2) {
        //реализуйте метод getDistance
    }
}

public class PolygonalLine {
    public double getLength()
    {
        //реализуйте метод получение длины ломаной линии
        return 0;
    }

    public void addPoint(Point p){
        //реализуйте метод добавления точки
    }
}
//#endregion Task


