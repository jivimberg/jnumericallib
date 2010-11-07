package methods;

import java.awt.geom.Point2D;

public class Fourier {

	public double fourierDiscreto(Point2D[] points, int m, double L, double x) {

		final double N = points.length;
		double[] aK = new double[m];
		double[] bK = new double[m];

		double a0 = 0.0;
		float f = (float) (2 / (N + 1));
		for (int i = 0; i < N; i++) {
			a0 += points[i].getY();
		}
		a0 *= f;

		for (int k = 1; k < m; k++) {
			for (int i = 1; i < N; i++) {
				aK[k] += f * (points[i].getY() * Math.cos((Math.PI * k * points[i].getX()) / L));
				bK[k] += f * (points[i].getY() * Math.sin((Math.PI * k * points[i].getX()) / L));
			}
		}

		double result = a0;
		for (int k = 1; k < m; k++) {
			result += aK[k] * (Math.cos((k * Math.PI * x) / L)) + bK[k] * (Math.sin((k * Math.PI * x) / L));
		}
		return result;
	}
	
	public static void main(String args[]) {
		Fourier fourier = new Fourier();
		
		double result = fourier.fourierDiscreto(createPoints(), 5, 5, 2);
		System.out.println(result);
	}
	
	private static Point2D[] createPoints() {
		Point2D[] points = new Point2D[5];
		points[0] = new Point2D.Double(1,1);
		points[1] = new Point2D.Double(2,5);
		points[2] = new Point2D.Double(3,2);
		points[3] = new Point2D.Double(6,5);
		points[4] = new Point2D.Double(9,8);
		return points;
	}
}
