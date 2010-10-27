package tests.interpolators;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import methods.interpolators.CubicSpline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class CubicSplineTestCase {

	private static final double ERROR = 0.000001;

	private List<Point2D.Double> points;
	private double[][] expectedValues;
	
	public CubicSplineTestCase(List<Point2D.Double> points, double[][] expectedValues) {
		this.points = points;
		this.expectedValues = expectedValues;
	}
	
	@Test
	public void testCubicSpline() {
		CubicSpline li = new CubicSpline();
    	double[][] result = li.interpolate(points);
    	
    	for (int i = 0; i < result.length; i++) {
    		for(int j = 0; j < 4; j++) {
    			testAssertEquals(expectedValues[i][j], result[i][j]);	
    		}
    	}
    	printResult(result);
	}
	
	private void printResult(double[][] result) {
		for(int i = 0; i < result.length; i++) {
			System.out.println("a = "+result[i][3]);
			System.out.println("b = "+result[i][2]);
			System.out.println("c = "+result[i][1]);
			System.out.println("d = "+result[i][0]);
		}
	}
	
	private void testAssertEquals(double expected, double actual) {
    	assertEquals("The index founded was not the expected", expected, actual, ERROR);
	}
	
	@Parameters
    public static Collection<Object[]> data() {
        Object[][] parameters = new Object[][] {{createPoints(), createExpectedValues()}};    
    	return Arrays.asList(parameters);
    }
	
	private static List<Point2D.Double> createPoints() {
		List<Point2D.Double> points = new ArrayList<Point2D.Double>();
    	points.add(new Point2D.Double(40, 390));
    	points.add(new Point2D.Double(45, 340));
    	points.add(new Point2D.Double(50, 290));
    	points.add(new Point2D.Double(55, 250));
    	points.add(new Point2D.Double(60, 210));
    	points.add(new Point2D.Double(65, 180));
    	points.add(new Point2D.Double(70, 160));
        return points;
    }
	
	private static double[][] createExpectedValues() {
		double[][] result = new double[6][4];
		result[0][0] = 390.0;
		result[0][1] = -9.84871794871795;
		result[0][2] = 0.0;
		result[0][3] = -0.006051282051282051;
		result[1][0] = 340.0;
		result[1][1] = -10.302564102564103;
		result[1][2] = -0.09076923076923077;
		result[1][3] = 0.030256410256410255;
		result[2][0] = 290.0;
		result[2][1] = -8.941025641025641;
		result[2][2] = 0.3630769230769231;
		result[2][3] = -0.03497435897435897;
		result[3][0] = 250.0;
		result[3][1] = -7.933333333333334;
		result[3][2] = -0.16153846153846155;
		result[3][3] = 0.029641025641025644;
		result[4][0] = 210.0;
		result[4][1] = -7.325641025641026;
		result[4][2] = 0.2830769230769231;
		result[4][3] = -0.0035897435897435936;
		result[5][0] = 180.0;
		result[5][1] = -4.764102564102564;
		result[5][2] = 0.2292307692307692;
		result[5][3] = -0.015282051282051281;
		return result;
	}
}
