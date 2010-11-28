package methods;

/**
 * La clase Funcion2 sirve para representar una funcion que recibe dos parametros, x e y. 
 * El usuario debe extender de esta clase e implementar el metodo eval.
 * 
 * <b>Ejemplo:</b>
 * <pre>
 * La funcion cos(x) - y^3 se modela de la siguiente manera
 * 
 * final Funcion f1 = new Funcion(){
 *           public double eval(double x, double y){
 *          	return Math.cos(x) - Math.pow(y, 3);
 *           }
 *      };
 * <pre>
 */
public abstract class Funcion2 {

	/**
	 * Metodo que evalua la funcion en los puntos dados.
	 * 
	 * @param x el primer valor en el que la funcion va a ser evaluada
	 * @param y el segundo valor en el que la funcion va a ser evaluada
	 * @return el valor de la funcion evaluada en los puntos x e y
	 */
    public abstract double eval (double x, double y);
    
}