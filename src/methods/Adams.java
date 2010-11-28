package methods;

public class Adams {
	private RungeKutta runge = new RungeKutta();

	private double[] bashforthCoefficients;
	private double divisor;
	private double[] moultonCoefficients;

	private Adams(double[] bashforth, double[] moulton, double divisor) {
		this.bashforthCoefficients = bashforth;
		this.moultonCoefficients = moulton;
		this.divisor = divisor;
	}
	
	public static Adams createOrder5() {
		return new Adams(COEFF_ORDER_5, MOULTON_COEFF_ORDER_5, DIVISOR_ORDER_5);
	}

	public static Adams createOrder6() {
		return new Adams(COEFF_ORDER_6, MOULTON_COEFF_ORDER_6, DIVISOR_ORDER_6);
	}

	public static Adams createOrder7() {
		return new Adams(COEFF_ORDER_7, MOULTON_COEFF_ORDER_7, DIVISOR_ORDER_7);
	}

	private double computeAdamsBashforth(double h, double yn, double[] history) {
		double delta = 0;
		for (int i = 0, n = bashforthCoefficients.length - 1; i < bashforthCoefficients.length; i++, n--) {
			delta += bashforthCoefficients[i] * history[n];
		}
		return yn + h * delta / divisor;
	}

	private double[] buildHistory(Funcion2 f, double x0, double h, double[] y) {
		double[] history = new double[bashforthCoefficients.length];
		for (int i = 0; i < bashforthCoefficients.length; x0 += h, i++) {
			history[i] = f.eval(x0, y[i]);
		}
		return history;
	}

	private static boolean hasConverged(double y0, double y1, double epsilon) {
		// Ajusto epsilon para evitar errores por cancelacion por resta
		if (Math.abs(y0) > 1.0 && Math.abs(y1) > 1.0) {
			epsilon *= Math.abs(y1);
		}
		return Math.abs(y0 - y1) < epsilon;
	}

	/**
	 * Resuelve una ecuacion diferencial ordinaria de primer orden utilizando el
	 * metodo de Adams-Bashforth. El problema debe estar planteado como un
	 * problema de valor inicial: y' = f(x,y) y(x0)=y0
	 * 
	 * El metodo retorna el valor de y(xn).
	 * 
	 * @param f la ecuacion a resolver. De la forma y' = f(x,y)
	 * @param x0 valor inicial de la variable X
	 * @param y0 valor inicial de la funcion en el punto x0
	 * @param xn valor de la funcion en Xn
	 * @return yn (el valor de la funcion en xn)
	 */
	public double adamsBashforth(Funcion2 f, double x0, double y0, double xn) {
		int order = bashforthCoefficients.length;
		double h = (xn - x0) / order;

		// Calculo los valores iniciales usando RungeKutta
		double[] y = runge.tabulateOrder4(f, x0, y0, x0 + h * (order - 1),
				order - 1);

		// Construyo la historia: f(x[i], y[i])
		double[] history = buildHistory(f, x0, h, y);
		return computeAdamsBashforth(h, y[y.length - 1], history);
	}

	/**
	 * Resuelve una ecuacion diferencial ordinaria de primer orden utilizando el
	 * metodo de Adams-Moulton. El problema debe estar planteado como un
	 * problema de valor inicial: y' = f(x,y) y(x0)=y0
	 * 
	 * y(xn) = ?
	 * 
	 * @param f la ecuacion a resolver. De la forma y' = f(x,y)
	 * @param x0 valor inicial de la variable X
	 * @param y0 valor inicial de la funcion en el punto x0
	 * @param xn valor de la funcion en Xn
	 * @param maxIter maximo numero de iteraciones del corrector
	 * @param tolerance tolerancia utilizada para detener el corrector.
	 * @return yn (el valor de la funcion en xn)
	 */
	public double adamsMoulton(Funcion2 f, double x0, double y0, double xn,
			int maxIter, double tolerance) {
		int order = bashforthCoefficients.length;
		double h = (xn - x0) / order;

		// Calculo los valores iniciales usando RungeKutta
		double[] y = runge.tabulateOrder4(f, x0, y0, x0 + h * (order - 1),
				order - 1);

		// Construyo la historia: f(x[i], y[i])
		double[] history = buildHistory(f, x0, h, y);
		double yn1;

		// Calculo usando el predictor de AdamsB-Bashforth
		yn1 = computeAdamsBashforth(h, y[y.length - 1], history);

		// Aplico correcciones sucesivas usando Adams-Moulton
		double last;
		for (int i = 0; i < maxIter; i++) {
			last = yn1;
			yn1 = computeAdamsMoulton(f, xn, yn1, h, y, history);
			if (hasConverged(last, yn1, tolerance)) {
				break;
			}
		}

		return yn1;
	}

	private double computeAdamsMoulton(Funcion2 f, double xn,
			double bashforth, double h, double[] y, double[] history) {
		double delta = 0.0;
		int n = moultonCoefficients.length - 1;

		for (int i = 1; i < moultonCoefficients.length; i++, n--) {
			delta += moultonCoefficients[i] * history[n];
		}
		delta += f.eval(xn, bashforth) * moultonCoefficients[0];
		return y[y.length - 1] + h * delta / divisor;
	}

	public static final double[] COEFF_ORDER_5 = { 1901.0, -2774.0, 2616.0,
			-1274.0, 251.0 };
	public static final double DIVISOR_ORDER_5 = 720.;

	public static final double[] COEFF_ORDER_6 = { 4277., -7923., 9982.,
			-7298., 2877., -475. };
	public static final double DIVISOR_ORDER_6 = 1440;

	public static final double[] COEFF_ORDER_7 = { 198721.0, -447288.0,
			705549.0, -688256.0, 407139.0, -134472.0, 19087.0 };
	public static final double DIVISOR_ORDER_7 = 60480.0;

	public static final double[] MOULTON_COEFF_ORDER_5 = { 251.0, 646.0,
			-264.0, 106.0, -19.0 };
	public static final double[] MOULTON_COEFF_ORDER_6 = { 475.0, 1427.0,
			-798.0, 482.0, -173.0, 27.0 };
	public static final double[] MOULTON_COEFF_ORDER_7 = { 19087.0, 65112.0,
			-46461.0, 37504.0, -20211.0, 6312.0, -863.0 };

}
