package jMathLib.methods.interpolators;

import model.FunctionType;
import model.functions.MyFunction;
import model.functions.Utils;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * 
 * @author jivimberg
 * @author danielorozco87
 *
 */
public class NewtonInterpolator implements Interpolator {

     /**
     * Método interpolador mediante el uso de Newton-Raphson
     */

	public double[] divDif(List<Point2D.Double> points) {
		int N = points.size();
		double[][] M = new double[N][N];
		for (int i = 0; i < N; i++)
			M[i][0] = (points.get(i).y);
		for (int j = 1; j < N; j++)
			for (int i = 0; i < N - j; i++)
				M[i][j] = (M[i + 1][j - 1] - M[i][j - 1]) / (points.get(i + j).x - (points.get(i).x));
		return M[0];
	}

	public final MyFunction horner(final double[] a, final List<Point2D.Double> points) {
		return new MyFunction() {
			public double resolve(double t) {
				int n = a.length;
				double v = a[n - 1];
				for (int i = 1; i < n; i++)
					v = v * (t - points.get(n - 1 - i).x) + a[n - 1 - i];
				return v;
			}

            @Override
            public MyFunction derive() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public FunctionType getType() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isConstant() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public double getCoefficient() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public MyFunction getFunctionWithoutCoefficient() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

        };
	}

	public final double[][] interpolate(final List<Point2D.Double> points) {
		final double[] a = divDif(points);
		return new MyFunction() {
			public double resolve(double t) {
				return horner(a, points).resolve(t);
			}

            @Override
            public MyFunction derive() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public FunctionType getType() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isConstant() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public double getCoefficient() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public MyFunction getFunctionWithoutCoefficient() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
	}

    public static void main (String[] args){
        List<Point2D.Double> points = Utils.createPoints();
        NewtonInterpolator li = new NewtonInterpolator();
        System.out.println(li.interpolate(points).resolve(1));
        System.out.println(li.interpolate(points).resolve(2));
        System.out.println(li.interpolate(points).resolve(3));
        System.out.println(li.interpolate(points).resolve(4));
        System.out.println(li.interpolate(points).resolve(5));
        System.out.println(li.interpolate(points).resolve(6));
    }
}
