package methods.interpolators;

import methods.Function;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Aug 14, 2010
 * Time: 3:51:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Interpolator {

    public Function interpolate( List<Point2D.Double> points);
}
