package methods.interpolators;

import methods.Funcion;

import java.awt.geom.Point2D;
import java.util.List;

public interface Interpolador {

    public Funcion interpolate( List<Point2D.Double> points);
}
