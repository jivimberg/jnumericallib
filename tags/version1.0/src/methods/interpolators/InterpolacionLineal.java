package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

import methods.Funcion;

/**
 * La <b>Interpolacion Lineal</b> es un caso particular de la Interpolacion general de Newton. Con el polinomio 
 * de interpolacion de Newton se logra aproximar un valor de la función f(x) en un valor desconocido de x. El 
 * caso particular, para que una interpolacion sea lineal, es que el que se utiliza un polinomio de interpolacion 
 * de grado 1, y se denota de la siguiente manera:
 * <br>
 * <img src="../../resources/formulaInterpolacionLineal.png" alt=""/>
 *
 */
public class InterpolacionLineal {
	
	/**
     * Interpola la funcion usando <b>Interpolacion Lineal</b>
     * @param points Set de puntos a usarse para la interpolacion
     * @return Funcion generada por la interpolacion
     */

	public static final Funcion interpolate(final List<Point2D.Double> points) {
		return new Funcion() {
			public double eval(double x) {
				double w = 0;
				if (x <= points.get(0).x)
					w = points.get(0).y + (points.get(1).y - points.get(0).y) * (x - points.get(0).x) / (points.get(1).x - points.get(0).x);
				if (x > points.get(0).x) {
					int i = 0;
					for (int k = 1; k < points.size(); k++) {
						if (x - points.get(i).x > 0)
							i++;
					}
					i--;
					w = points.get(i).y + (points.get(i+1).y - points.get(i).y) * (x - points.get(i).x) / (points.get(i+1).x - points.get(i).x);
				}
				return w;
			}
        };
	}

}
