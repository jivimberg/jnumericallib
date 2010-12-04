package methods;

/**
 * La clase Funcion sirve para representar una funcion. 
 * El usuario debe extender de esta clase e implementar el metodo eval.
 * 
 * <b>Ejemplo:</b>
 * <pre>
 * La funcion cos(x) - x^3 se modela de la siguiente manera
 * 
 * final Funcion f1 = new Funcion(){
 *           public double eval(double x){
 *          	return Math.cos(x) - Math.pow(x, 3);
 *           }
 *      };
 * <pre>
 */
public abstract class Funcion {

	/**
	 * Metodo que evalua la funcion en el punto dado.
	 * 
	 * @param x el valor en el que la funcion va a ser evaluada
	 * @return el valor de la funcion evaluada en el punto x
	 */
    public abstract double eval (double x);

}
