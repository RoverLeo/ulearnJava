package U04_OOP_Basics.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.lang.*;
import java.util.*;

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

public class UnitTestS029 {

    //PolygonalLineTest
    @Test
    public void getLength() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(3, 0);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        Line line3 = new Line(p3, p1);
        PolygonalLine p_line = new PolygonalLine(Arrays.asList(line1, line2, line3));
        Assert.assertEquals(12, p_line.getLength(), 10e-5);
    }

    //PointTest
    @Test
    public void getXTest() {
        Point p = new Point(1, 2);
        Assert.assertEquals(1, p.getX());
    }

    @Test
    public void getYTest() {
        Point p = new Point(1, 2);
        Assert.assertEquals(2, p.getY());
    }

    @Test
    public void getDistanceTest_1() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5, 0);
        Assert.assertEquals(5, p1.getDistance(p2), 10e-5);
    }

    @Test
    public void getDistanceTest_2() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Assert.assertEquals(5, p1.getDistance(p2), 10e-5);
    }

    @Test
    public void toStringTest() {
        Point p1 = new Point(3, 1);
        Assert.assertEquals("[ 3, 1 ]", p1.toString());
    }

    @Test
    public void hasPointTest() {
        Line line = new Line(new Point(0, 0), new Point(100, 100));
        Point p = new Point(50, 50);
        Assert.assertTrue(line.hasPoint(p));
    }

    @Test
    public void getLengthTest() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(3, 0);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        Line line3 = new Line(p3, p1);
        PolygonalLine p_line = new PolygonalLine(Arrays.asList(line1, line2, line3));
        Assert.assertEquals(12, p_line.getLength(), 10e-5);
    }

    @Test
    public void addPointTest() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 0));
        Line line2 = new Line(new Point(10, 0), new Point(10, 10));
        PolygonalLine line = new PolygonalLine(new ArrayList<Line>(Arrays.asList(line1, line2)));
        line.addPoint(new Point(20, 10));
        Assert.assertEquals(30, line.getLength(), 10e-5);
    }
}

//#region Task
public class Line {
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getStartPoint() {
        return p1;
    }

    public Point getEndPoint() {
        return p2;
    }

    public String toString() {
        return p1.toString() + ", " + p2.toString();
    }

    public double getLength() {
        return p1.getDistance(p2);
    }

    public boolean hasPoint(Point p) {
        return (p.x - p1.x) / (p2.x - p1.x) == (p.y - p1.y) / (p2.y - p1.y);
    }
}

public class Point {
    protected final int x;
    protected final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }

    public String toString() {
        return "[ " + x + ", " + y + " ]";
    }
}

public class PolygonalLine {
    List<Line> lines;

    public PolygonalLine(List<Line> lines) {
        this.lines = lines;
    }

    public double getLength() {
        double distance = 0;
        for (Line line : lines) {
            distance += line.getLength();
        }
        return distance;
    }

    public void addPoint(Point p) {
        Point point = lines.get(lines.size() - 1).getEndPoint();
        Line line = new Line(point, p);
        lines.add(line);

    }
}
//#endregion Task


