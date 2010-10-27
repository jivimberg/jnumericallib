package methods;

import exceptions.RootNotFoundException;

public class Bisection {
	
	private static final String METHOD_NAME = "Bisection";

    /**
     * El m√©todo de Bisecci√≥n encuentra la ra√≠z existente entre 2 valores conocidos.
     * Condiciones suficientes: que haya una raÌz en el intervalo, que esa raÌz sea ˙nica f'(x) != 0 para todo x perteneciente a  [left, right],
     * la f debe ser continua y f(left) debe tener distinto signo que f(right) 
     *
     * @param a          el valor de la izquierda
     * @param b        el valor de la derecha
     * @param error      el nivel de error tolerado
     * @param iterations el m√°ximo n√∫mero de iteraciones deseadas
     */

    public static double findRoot(Function f, double a, double b, double error, int iterations)
            throws RootNotFoundException {
        int i = 1;
        double fa = f.eval(a); // f evaluated in a
        
        while (i <= iterations) {
            double p = a + (b - a) / 2; //middle point of the interval
            double fp = f.eval(p); //f evaluated in p
            //if fp is root OR the interval is smaller than the tolerated error
            if (fp == 0 || (b - a) / 2 < error) {
                return p;
            }
            i += 1;
            //if fa and fp has the same sign
            if (fa * fp > 0) {
                a = p;
                fa = fp;
            } else {
                b = p;
            }
        }
        throw new RootNotFoundException(METHOD_NAME);
    }
}
