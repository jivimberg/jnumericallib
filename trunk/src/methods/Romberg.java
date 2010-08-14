package jMathLib.methods;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 7, 2010
 * Time: 4:00:05 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class Romberg{

    /**
     * Romberg genera de un arreglo triangular consistente de las
     *  estimaciones númericas de la integral definida
     *
     * @param from from value.
     * @param to to value.
     * @param maxIterations the maximum steps
     * @author Juan Ignacio Vimberg
     */

    public void calculate(Function f, double from, double to, int maxIterations) {
        double[][] r = new double[3][20];
        double h = to - from;
        r[1][1] = (h / 2) * (f.eval(from) + f.eval(to));
        System.out.println(r[1][1]);
        for (int i = 2; i <= maxIterations; i++) {
            r[2][1] = 0.5 * (r[1][1] + sum(f, from, i, h));
            System.out.print(r[2][1] + "  ");
            for (int j = 2; j <= i; j++) {
                r[2][j] = r[2][j - 1] + (r[2][j - 1] - r[1][j - 1]) / (Math.pow(4, j - 1) - 1);
                System.out.print(r[2][j] + "  ");
            }
            h = h / 2;
            for (int j = 1; j <= i; j++) {
                r[1][j] = r[2][j];
            }
            System.out.println("");
        }
    }

    private double sum(Function f, double a, int n, double h) {
        double result = 0;
        for (int i = 1; i <= Math.pow(2, n - 2); i++) {
            result += f.eval(a + (i - 0.5) * h);
        }
        return result * h;
    }

    public static void main(String[] args) {
//        Romberg romberg = new Romberg();
//        Function f = new Function();
//        romberg.calculate(f, -0.5, 0.5, 10);
	}
}