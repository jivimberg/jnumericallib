package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import methods.Function;

public class NewtonInterpolator implements Interpolator {

      /**
     * Método interpolador mediante el uso de Newton-Raphson
     * @author Juan Ignacio Vimberg
     */

	private double[] divDif(List<Point2D.Double> points) {
		int N = points.size();
		double[][] M = new double[N][N];
		for (int i = 0; i < N; i++)
			M[i][0] = (points.get(i).y);
		for (int j = 1; j < N; j++)
			for (int i = 0; i < N - j; i++)
				M[i][j] = (M[i + 1][j - 1] - M[i][j - 1]) / (points.get(i + j).x - (points.get(i).x));
		return M[0];
	}

	private final Function horner(final double[] a, final List<Point2D.Double> points) {
		return new Function() {
			public double eval(double x) {
				int n = a.length;
				double v = a[n - 1];
				for (int i = 1; i < n; i++)
					v = v * (x - points.get(n - 1 - i).x) + a[n - 1 - i];
				return v;
			}
        };
	}

	public final Function interpolate(final List<Point2D.Double> points) {
		final double[] a = divDif(points);
		return new Function() {
			public double eval(double x) {
				return horner(a, points).eval(x);
			}
        };
	}

    public static void main (String[] args){
        List<Point2D.Double> points = createPoints();
        NewtonInterpolator li = new NewtonInterpolator();
        Function function = li.interpolate(points);
        printResult(function);
    }
    
    private static void printResult(Function function) {
    	for(int i = 0; i<7; i++) {
    		System.out.println("f("+i+") = " + function.eval(i));
    	}
    }
    
    public static List<Point2D.Double> createPoints() {
        List<Point2D.Double> points = new ArrayList<Point2D.Double>();
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(2, 4));
        points.add(new Point2D.Double(4, 0));
        points.add(new Point2D.Double(5, 8));
        return points;
    }
}
