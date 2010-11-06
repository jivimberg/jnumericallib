package methods.interpolators;

import methods.Funcion;

import java.awt.geom.Point2D;
import java.util.List;


/**
 *Interfaz que representa a los interpoladores. Interpolar es unir una serie de puntos por medio de una funcion.
 *
 */
public interface Interpolador {

	/**
	 * Interpola los puntos, generando una funcion.
	 * @param points conjuntos de puntos (x, y)
	 * @return Funcion que interpola los points
	 */
    public Funcion interpolate(List<Point2D.Double> points);
}
