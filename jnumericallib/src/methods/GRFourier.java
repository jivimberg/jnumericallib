package jMathLib.methods;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 7, 2010
 * Time: 4:41:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class GRFourier {

    /**
     * El metodo de Goertzel-Reinsh para funciones periodicas aproxima una
     * funcion en base a otra funcion creada a partir de funciones base entre
     * senos y cosenos de una manera más eficiente que el método discreto de
     * Fourier
     *
     * @param f function
     * @param n es la cantidad de funciones base que voy a utilizar
     * @param m es el nro en el que divido el intervalo (-L ; L)
     * @param L es la mitad el período en el cual la funcion se repite.
     * @author Juan Ignacio Vimberg
     */

    double[] a;
    double[] b;

    public Function solve(Function f, int n, int m, double L) {

        double sin = 0;
        double cos = 1;
        double sin1 = Math.sin(2 * Math.PI / (m + 1));
        double cos1 = Math.cos(2 * Math.PI / (m + 1));

        double[][] u = new double[m + 3][n + 1];
        double[] a = new double[n];
        double[] b = new double[n];

        // xi
        double[] x = new double[m + 1];
        for (int i = 0; i <= m; i++) {
            x[i] = (2 * L * i / m);
        }

        double[] y = new double[m + 1];
        for (int i = 0; i <= m; i++) {
            y[i] = f.eval(x[i]);
        }

        for (int j = 0; j < n; j++) {
            for (int i = m; i >= 0; i--) {
                u[i][j] = y[i] + 2 * u[i + 1][j] * cos - u[i + 2][j];
            }
            a[j] = 2d / (m + 1) * (y[0] + u[1][j] * cos - u[2][j]);
            b[j] = 2d / (m + 1) * u[1][j] * sin;
            double sinAux = sin;
            sin = sin * cos1 + cos * sin1;
            cos = cos * cos1 - sinAux * sin1;
        }

        Function[] terms = new Function[2 * n];
//        terms[0] = new Polynomial(0, new double[]{a[0] / 2});
//        terms[1] = new Polynomial(0, new double[]{b[0]});
//        for (int i = 1; i < n; i++) {
//            terms[2 * i] = new Compound(new Polynomial(1, new double[]{0, a[i]}), new Cos(new Polynomial(1, new double[]{0,
//                    i * Math.PI / L})));
//            terms[2 * i + 1] = new Compound(new Polynomial(1, new double[]{0, b[i]}), new Sin(new Polynomial(1, new double[]{0,
//                    i * Math.PI / L })));
//		}
//		return new Sum(terms);
        return null;
	}

    public static void main (String[] args){
//         GRFourier gf = new GRFourier();
//        Function f = new Function();
//        Function result = gf.solve(f, 1, 3, 5);
//        System.out.println(result.resolve(0));
    }
}
