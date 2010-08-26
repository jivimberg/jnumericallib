package jMathLib.methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * 
 * @author jivimberg
 * @author danielorozco87
 *
 */
public interface Interpolator {

    public double[][] interpolate(List<Point2D.Double> points);
}
