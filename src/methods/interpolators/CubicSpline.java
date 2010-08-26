package jMathLib.methods.interpolators;

import jMathLib.methods.Function;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: May 26, 2010
 * Time: 6:53:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CubicSpline implements Interpolator{
    /**
     * Una spline cúbica es una spline compuesta de segmentos de polinomios de tercer orden que pasan a traves de una serie de
     * puntos de control. La segunda derivada de cada polinomio es comunmente cero en cada punto final, esto provee las condiciones de contorno que
     * completan el sistema de ecuaciones. Esto produce lo llamado spline cúbica "natural" y lleva a un sistema simple tridiagonal que puede
     * ser facilmente resuelto dando los coeficientes de los polinomios
     *
     * @author Juan Ignacio Vimberg
     */

    /*
        * Interpola la función usando Spline Cúbica Natural
        *
        * @param points  Set de puntos a usarse en la interpolación
        */
    public Function interpolate(@NotNull List<Point2D.Double> points) {

        int n = points.size();

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

//        return s;
        return null;
    }

    private double[] solveTridiagonalSystem(int n, double[] h, double[] w) {
        double[] l = new double[n];
        double[] u = new double[n];
        double[] z = new double[n];

        l[1] = 2 * (h[1] + h[0]);
        u[1] = w[1] - w[0];

        for (int i = 2; i < n; i++) {
            l[i] = 2 * (h[i] + h[i - 1]) - h[i - 1] * h[i - 1] / l[i - 1];
            u[i] = w[i] - w[i - 1] - h[i - 1] * u[i - 1] / l[i - 1];
        }

        z[n - 1] = u[n - 1] / l[n - 1];
        for (int i = n - 2; i > 0; i--) {
            z[i] = (u[i] - h[i] * z[i + 1]) / l[i];
        }
        z[0] = 0;
        return z;
    }

    public static void main (String[] args){
//        List<Point2D.Double> points = Utils.createPoints();
//        CubicSpline li = new CubicSpline();
//        double[][] ds = li.interpolate(points);
//        for (int i = 0; i < ds.length; i++) {
//            for(int j = 0; j < ds.length; j++){
//                System.out.println(ds[i][j]);
//            }
//
//        }
    }
}
