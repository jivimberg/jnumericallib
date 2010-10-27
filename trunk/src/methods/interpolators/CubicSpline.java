package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Una spline cubica es una spline compuesta de segmentos de polinomios de tercer orden que pasan a traves de una serie de
 * puntos de control. La segunda derivada de cada polinomio es comunmente cero en cada punto final, esto provee las condiciones de contorno que
 * completan el sistema de ecuaciones. Esto produce lo llamado spline cubica "natural" y lleva a un sistema simple tridiagonal que puede
 * ser facilmente resuelto dando los coeficientes de los polinomios.
 * 
 * @author jivimberg
 * @author danielorozco87
 */

public class CubicSpline{
	
	private static final String METHOD_NAME = "Cubic Spline";
    
    /**
     * Interpola la funcion usando Spline Cubica Natural
     * @param points  Set de puntos a usarse en la interpolacion
     */
	
    public double[][] interpolate(List<Point2D.Double> points) {

        int n = points.size();

        double[] h = new double[n];
        double[] w = new double[n];

        for (int i = 0; i < n - 1; i++) {
        	//Hi es Xi+1 - Xi
            h[i] = points.get(i + 1).x - points.get(i).x;
            //Wi es 6 * (Yi+1 - Yi) / Hi
            w[i] = 6 * (points.get(i + 1).y - points.get(i).y) / h[i];
        }

        double[] x = solveTridiagonalSystem(n, h, w);

        double[][] s = new double[n - 1][4];
        for (int i = 0; i < n - 1; i++) {
            s[i][3] = (x[i + 1] - x[i]) / (6 * h[i]);
            s[i][2] = x[i] / 2;
            s[i][1] = -(h[i] * x[i]) / 3 - (h[i] * x[i + 1] / 6) + (points.get(i + 1).y - points.get(i).y) / h[i];
            s[i][0] = points.get(i).y;
        }
        return s;
    }

    private double[] solveTridiagonalSystem(int n, double[] h, double[] w) {
        double[] a = new double[n];
        double[] b = new double[n];
        double[] c = new double[n];

        a[1] = 2 * (h[1] + h[0]);
        b[1] = w[1] - w[0];

        for (int i = 2; i < n; i++) {
            a[i] = 2 * (h[i] + h[i - 1]) - h[i - 1] * h[i - 1] / a[i - 1];
            b[i] = w[i] - w[i - 1] - h[i - 1] * b[i - 1] / a[i - 1];
        }

//        c[n - 1] = b[n - 1] / a[n - 1];
        c[n - 1] = 0;
        for (int i = n - 2; i > 0; i--) {
            c[i] = (b[i] - h[i] * c[i + 1]) / a[i];
        }
        c[0] = 0;
        return c;
    }

    public static void main (String[] args){
    	List<Point2D.Double> points = new ArrayList<Point2D.Double>();
    	points.add(new Point2D.Double(40, 390));
    	points.add(new Point2D.Double(45, 340));
    	points.add(new Point2D.Double(50, 290));
    	points.add(new Point2D.Double(55, 250));
    	points.add(new Point2D.Double(60, 210));
    	points.add(new Point2D.Double(65, 180));
    	points.add(new Point2D.Double(70, 160));
    	
    	CubicSpline li = new CubicSpline();
    	double [][] result = li.interpolate(points);
    	
    	for (int i = 0; i < result.length; i++) {
    		System.out.println("d = "+result[i][0]);
    		System.out.println("c = "+result[i][1]);
    		System.out.println("b = "+result[i][2]);
    		System.out.println("a = "+result[i][3]);
      }
    }
}
