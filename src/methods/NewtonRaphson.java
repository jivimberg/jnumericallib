package methods;

import exceptions.RootNotFoundException;

public class NewtonRaphson{
	
	private static final String METHOD_NAME = "NewtonRaphson";
	
    /**
     * Newton-Raphson method finds a root by sucesive derivations.
     * @author Juan Ignacio Vimberg
     */

    /**
     * Este método encuentra la raíz de la función por medio de Newton Raphson
     *
     * @param p0            punto de inicio. Debe ser lo más cercano posible a la raíz
     * @param error         margen de error tolerado
     * @param maxIterations número máximo de iteraciones permitidas
     */
    public static double findRoot(Function f, Function fderived, double p0, double error, int maxIterations)
            throws RootNotFoundException {
        int iterations = 1;
        double p = p0;
        while (iterations < maxIterations) {
            //Xn+1 = Xn - f(x) / f'(x)
            double p1 = p - f.eval(p) / fderived.eval(p);
            if (Math.abs(f.eval(p1)) < error) {
                return p1;
            }
            p = p1;
            iterations++;
        }
        throw new RootNotFoundException(METHOD_NAME);
    }
}
