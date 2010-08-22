package jMathLib.methods.interpolators;

import model.FunctionType;
import model.functions.MyFunction;
import model.functions.Utils;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * 
 * @author jivimberg
 * @author danielorozco87
 *
 */
public class LinearInterpolator implements Interpolator{

	public final double[][] interpolate(final List<Point2D.Double> points) {
		return new MyFunction() {
			public double resolve(double u) {
				double w = 0;
				if (u <= points.get(0).x)
					w = points.get(0).y + (points.get(1).y - points.get(0).y) * (u - points.get(0).x) / (points.get(1).x - points.get(0).x);
				if (u > points.get(0).x) {
					int i = 0;
					for (int k = 1; k < points.size(); k++) {
						if (u - points.get(i).x > 0)
							i++;
					}
					i--;
					w = points.get(i).y + (points.get(i+1).y - points.get(i).y) * (u - points.get(i).x) / (points.get(i+1).x - points.get(i).x);
				}
				return w;
			}


            @Override
            public MyFunction derive() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public FunctionType getType() {
                return FunctionType.INTERPOLATOR;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isConstant() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public double getCoefficient() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public MyFunction getFunctionWithoutCoefficient() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
	}

    public static void main (String[] args){
        List<Point2D.Double> points = Utils.createPoints();
        LinearInterpolator li = new LinearInterpolator();
        System.out.println(li.interpolate(points).resolve(1));
        System.out.println(li.interpolate(points).resolve(2));
        System.out.println(li.interpolate(points).resolve(3));
        System.out.println(li.interpolate(points).resolve(4));
        System.out.println(li.interpolate(points).resolve(5));
        System.out.println(li.interpolate(points).resolve(6));
    }

    

}
