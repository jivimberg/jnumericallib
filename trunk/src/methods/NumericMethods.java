package methods;

import exceptions.RaizNoEncontradaExcepcion;

/**
 * Esta clase sirve como unico punto de acceso a la libreria. Simplificando de esta manera su uso.
 *
 */

public class NumericMethods {
	
	/**
	 * Haya la raiz de una funcion aplicando el metodo de Biseccion. Para una mayor informacion 
	 * remitirse a la documentacion de la clase Biseccion
	 * @param f funcion de la que deseamos encontrar la raiz
	 * @param a valor de la izquierda
	 * @param b valor de la derecha
	 * @param error nivel de error tolerado
     * @param iterations el maximomo numero de iteraciones permitidas
	 * @return raiz de la funcion
     * @throws RaizNoEncontradaExcepcion en caso de que la raiz no haya podidod ser encontrada
     * o que la funcion no cumpla con las condiciones minimas necesarias
	 */
	public double bisection(Funcion f, double a, double b, double error, int iterations) 
		throws RaizNoEncontradaExcepcion
	{
		return Biseccion.findRoot(f, a, b, error, iterations);
	}
	
	/**
	 * Este metodo encuentra la raiz de la funcion por medio de Newton Raphson
	 * @param f la funcion a la cual le queremos hayar la raiz
     * @param derivedf la derivada de la funcion 
     * @param p0            punto de inicio. Debe ser lo mas cercano posible a la raiz
     * @param error         margen de error tolerado
     * @param iterations numero maximo de iteraciones permitidas
     * @return raiz de la funcion
     * @throws RaizNoEncontradaExcepcion en caso de que la raiz no haya podidod ser encontrada
     * o que la funcion no cumpla con las condiciones minimas necesarias
	 */
	public double newtonRaphson(Funcion f, Funcion derivedf, double p0, double error, int iterations) 
		throws RaizNoEncontradaExcepcion{
		return NewtonRaphson.findRoot(f, derivedf, p0, error, iterations);
	}
	
	/**
     * Integra por romberg una funcion.
     * @param f Función a integrar
     * @param a comienzo del intervalo
     * @param b fin del intervalo
     * @param k cantidad de intervalos
     * @return la integral calculada
     */
	public double romberg(Funcion f, double a, double b, int k){
		return Romberg.romberg(f, a, b, k);
	}

}
