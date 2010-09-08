package jMathLib.tests.interpolators;

import jMathLib.methods.Function;
import jMathLib.methods.interpolators.LinearInterpolator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Aug 25, 2010
 * Time: 8:19:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinearInterpolatorTest {

    @Test
    public void testLinearInterpolator(){
        List<Point2D.Double> points = createPoints();
        LinearInterpolator li = new LinearInterpolator();
        Function interpolation = li.interpolate(points);
        System.out.println(interpolation.eval(1));
        System.out.println(interpolation.eval(2));
        System.out.println(interpolation.eval(3));
        System.out.println(interpolation.eval(4));
        System.out.println(interpolation.eval(5));
        System.out.println(interpolation.eval(6));
    }

     public static List<Point2D.Double> createPoints() {
        List<Point2D.Double> points = new ArrayList<Point2D.Double>();
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(2, 2));
        points.add(new Point2D.Double(3, 3));
        points.add(new Point2D.Double(4, 4));
        return points;
    }
}
