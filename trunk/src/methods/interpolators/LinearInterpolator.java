package methods.interpolators;

import java.awt.geom.Point2D;
import java.util.List;

import methods.Function;

public class LinearInterpolator implements Interpolator{

	public final Function interpolate(final List<Point2D.Double> points) {
		return new Function() {
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
