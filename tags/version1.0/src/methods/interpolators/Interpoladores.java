package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

import methods.Funcion;

/**
 * Esta clase sirve como unico punto de acceso a la libreria para interpoladores. Simplificando de esta manera su uso.
 *
 */

public class Interpoladores {

	/**
     * Interpola la funcion usando <b>Interpolacion Lineal</b>
     * @param points Set de puntos a usarse para la interpolacion
     * @return Funcion generada por la interpolacion
     */
	public static Funcion interpolarLinealmente(final List<Point2D.Double> points){
		return InterpolacionLineal.interpolate(points);
	}
	
	/**
     * Interpola la funcion usando <b>Interpolacion de Newton</b>
     * @param points Set de puntos a usarse para la interpolacion
     * @return Funcion generada por la interpolacion
     */
	public static Funcion interpolarNewton(final List<Point2D.Double> points){
		return InterpolacionNewton.interpolate(points);
	}
	
	/**
     * Interpola la funcion usando <b>Spline Cubica Natural</b>
     * @param points Set de puntos a usarse para la interpolacion
     * @return Funcion generada por la interpolacion
     */
	public static Funcion splineCubica(final List<Point2D.Double> points){
		return SplineCubica.interpolate(points);
	}
}
