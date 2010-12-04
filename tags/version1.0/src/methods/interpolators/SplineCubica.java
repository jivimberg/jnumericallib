package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

import methods.Funcion;

/**
 * Una <b>Spline Cubica</b> es una Spline compuesta de segmentos de polinomios de tercer orden que pasan a 
 * traves de una serie de puntos de control. Una spline es una curva definida en porciones mediante polinomios.<br>
 * La segunda derivada de cada polinomio es comunmente cero en cada punto final, esto provee las condiciones de 
 * contorno que completan el sistema de ecuaciones. Esto produce lo llamado <b>Spline Cubica <i>Natural</i></b> 
 * y lleva a un sistema simple tridiagonal que puede ser facilmente resuelto dando los coeficientes de los polinomios.
 * <br>
 * <img src="../../resources/imagenSplineCubica.png" alt=""/>
 */

public class SplineCubica{
	
    /**
     * Interpola la funcion usando <b>Spline Cubica Natural</b>
     * @param points Set de puntos a usarse para la interpolacion
     * @return Funcion generada por la interpolacion
     */
	
    public static Funcion interpolate(final List<Point2D.Double> points) {
        final int n = points.size();
        final double[][] constants = calculateConstant(points);
        return new Funcion() {
        	public double eval(double x){
        		if(x<points.get(0).x || x>points.get(n-1).x) {
        			//TODO que devuelva una exception
        			return 0;
        		}
        		int i = 0;
        		for(; i<points.size() - 1; i++) {
        			if(x <= points.get(i + 1).x) break;
        		}
        		double constant3 = constants[i][3];
        		double constant2 = constants[i][2];
        		double constant1 = constants[i][1];
        		double constant0 = constants[i][0];
        		constant3 = constant3 * Math.pow(x - points.get(i).x, 3);
        		constant2 = constant2 * Math.pow(x - points.get(i).x, 2);
        		constant1 = constant1 * Math.pow(x - points.get(i).x, 1);
        		constant0 = constant0 * Math.pow(x - points.get(i).x, 0);
        		return  constant3 + constant2 + constant1 + constant0;
        	}
        };
    }
    
    private static double[][] calculateConstant(final List<Point2D.Double> points) {
    	final int n = points.size();

        double[] h = new double[n];
        double[] w = new double[n];

        for (int i = 0; i < n - 1; i++) {
            h[i] = points.get(i + 1).x - points.get(i).x;
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

    private static double[] solveTridiagonalSystem(int n, double[] h, double[] w) {
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

}
