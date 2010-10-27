package methods.interpolators;

import methods.Function;

import java.awt.geom.Point2D;
import java.util.List;

public interface Interpolator {

    public Function interpolate( List<Point2D.Double> points);
}
