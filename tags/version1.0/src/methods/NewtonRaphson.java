package methods;

import exceptions.RaizNoEncontradaExcepcion;

/**
 * <p>
 * El método de Newton (conocido también como el método de Newton-Raphson o el
 * método de Newton-Fourier) es un algoritmo eficiente para encontrar
 * aproximaciones de los ceros o raíces de una función real.
 * </p>
 * <br>
 * <p>
 * El método de Newton-Raphson es un método abierto, en el sentido de que su
 * convergencia global no está garantizada. La única manera de alcanzar la
 * convergencia es seleccionar un valor inicial lo suficientemente cercano a la
 * raíz buscada. Así, se ha de comenzar la iteración con un valor razonablemente
 * cercano al cero (denominado punto de arranque o valor supuesto). La relativa
 * cercanía del punto inicial a la raíz depende mucho de la naturaleza de la
 * propia función; si ésta presenta múltiples puntos de inflexión o pendientes
 * grandes en el entorno de la raíz, entonces las probabilidades de que el
 * algoritmo diverja aumentan, lo cual exige seleccionar un valor supuesto
 * cercano a la raíz. Una vez se ha hecho esto, el método linealiza la función
 * por la recta tangente en ese valor supuesto. La abscisa en el origen de dicha
 * recta será, según el método, una mejor aproximación de la raíz que el valor
 * anterior. Se realizarán sucesivas iteraciones hasta que el método haya
 * convergido lo suficiente.
 * </p>
 * <p>
 * Sea f : [a, b] -> R función derivable definida en el intervalo real [a, b].
 * Empezamos con un valor inicial x0 y definimos para cada número natural n
 * </p>
 * <br>
 * <img alt="NewtonRaphson Formule" src="..\resources\NR.png" > <br>
 * <p>
 * Donde f ' denota la derivada de f. Nótese que el método descrito es de
 * aplicación exclusiva para funciones de una sola variable con forma analítica
 * o implícita cognoscible. Existen variantes del método aplicables a sistemas
 * discretos que permiten estimar las raíces de la tendencia, así como
 * algoritmos que extienden el método de Newton a sistemas multivariables,
 * sistemas de ecuaciones, etc.
 * </p>
 */
public class NewtonRaphson{
	
	private static final String METHOD_NAME = "NewtonRaphson";
	

    /**
     * Este metodo encuentra la raiz de la funcion por medio de Newton Raphson
     *
     * @param f la funcion a la cual le queremos hayar la raiz
     * @param fderived la derivada de la funcion 
     * @param p0            punto de inicio. Debe ser lo mas cercano posible a la raiz
     * @param error         margen de error tolerado
     * @param maxIterations numero maximo de iteraciones permitidas
     * @return raiz de la funcion
     * @throws RaizNoEncontradaExcepcion en caso de que la raiz no haya podidod ser encontrada
     * o que la funcion no cumpla con las condiciones minimas necesarias
     */
    public static double findRoot(Funcion f, Funcion fderived, double p0, double error, int maxIterations)
            throws RaizNoEncontradaExcepcion {
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
        throw new RaizNoEncontradaExcepcion(METHOD_NAME);
    }
}
