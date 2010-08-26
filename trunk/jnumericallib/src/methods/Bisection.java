package jMathLib.methods;

import jMathLib.exceptions.RootNotFoundException;


/**
 * 
 * @author jivimberg
 * @author danielorozco87
 *
 */
public abstract class Bisection {

    /**
     * El método de Bisección encuentra la raíz existente entre 2 valores conocidos
     *
     * @param left          el valor de la izquierda
     * @param right        el valor de la derecha
     * @param error      el nivel de error tolerado
     * @param iterations el máximo número de iteraciones deseadas
     */

    public double calculate(Function f, double left, double right, double error, int iterations)
            throws RootNotFoundException {
        int i = 1;
        double fa = f.eval(left);
        while (i <= iterations) {
            double p = left + (right - left) / 2;
            double fp = f.eval(p);
            if (fp == 0 || (right - left) / 2 < error) {
                return p;
            }
            i += 1;
            if (fa * fp > 0) {
                left = p;
                fa = fp;
            } else {
                right = p;
            }
        }
        throw new RootNotFoundException();
    }

    public static void main(String[] args) throws RootNotFoundException {
//        MyFunction f = new Polynomial(3, new double[]{-10, 0, 4, 1});
//        Bisection b = new Bisection();
//        // VER EL EJEMPLO EN EL LIBRO
//        System.out.println(b.calculate(f, 1, 2, 0.0001, 14));
	}

}
