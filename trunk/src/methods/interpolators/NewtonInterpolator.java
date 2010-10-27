package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

import methods.Function;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 7, 2010
 * Time: 5:14:37 PM
 * To change this template use File | Settings | File Templates.
 */
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
//        List<Point2D.Double> points = Utils.createPoints();
//        NewtonInterpolator li = new NewtonInterpolator();
//        System.out.println(li.interpolate(points).eval(1));
//        System.out.println(li.interpolate(points).resolve(2));
//        System.out.println(li.interpolate(points).resolve(3));
//        System.out.println(li.interpolate(points).resolve(4));
//        System.out.println(li.interpolate(points).resolve(5));
//        System.out.println(li.interpolate(points).resolve(6));
    }
}
