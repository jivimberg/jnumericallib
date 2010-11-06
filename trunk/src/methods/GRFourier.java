package methods;


public class GRFourier {

	private static final String METHOD_NAME = "GRFourier";

    /**
     * El metodo de Goertzel-Reinsh para funciones periodicas aproxima una
     * funcion en base a otra funcion creada a partir de funciones base entre
     * senos y cosenos de una manera mas eficiente que el metodo discreto de
     * Fourier
     *
     * @param f funcion
     * @param n es la cantidad de funciones base que voy a utilizar
     * @param m es el nro en el que divido el intervalo (-L ; L)
     * @param L es la mitad el periodo en el cual la funcion se repite.
     * @param point es el punto en el cual se desea evaluar la funcion.
     */

    double[] a;
    double[] b;

    public double solve(Function f, int n, int m, double L, double point) {

        double sin = 0;
        double cos = 1;
        double sin1 = Math.sin(2 * Math.PI / (m + 1));
        double cos1 = Math.cos(2 * Math.PI / (m + 1));

        double[][] u = new double[m + 3][n + 1];
        double[] a = new double[n];
        double[] b = new double[n];

        double[] x = new double[m + 1];
        for (int i = 0; i <= m; i++) {
            x[i] = (2 * L * i / m);
        }

        double[] y = new double[m + 1];
        for (int i = 0; i <= m; i++) {
            y[i] = f.eval(x[i]);
        }

        for (int j = 0; j < n; j++) {
            for (int i = m; i >= 0; i--) {
                u[i][j] = y[i] + 2 * u[i + 1][j] * cos - u[i + 2][j];
            }
            a[j] = 2d / (m + 1) * (y[0] + u[1][j] * cos - u[2][j]);
            b[j] = 2d / (m + 1) * u[1][j] * sin;
            double sinAux = sin;
            sin = sin * cos1 + cos * sin1;
            cos = cos * cos1 - sinAux * sin1;
        }

        double[] terms = new double[2 * n];
//      Function[] terms = new Function[2 * n];
        
        terms[0] = a[0] / 2;
//		terms[0] = new Polynomial(0, new double[] { a[0] / 2 });
        terms[1] = b[0];
//		terms[1] = new Polynomial(0, new double[] { b[0] });
        for(int i = 1; i < n; i++) {
        	double constantA1 = i * Math.PI / L * point;
        	terms[2 * i] = constantA1;
//        	terms[2 * i] = new Polynomial(1, new double[] { 0, i * Math.PI / L });
        	
        	double constantB1 = i * Math.PI / L * point;
        	double constantB2 = Math.cos(constantB1);
        	double constantB3 = a[i] * constantB2;
        	terms[2 * i] = constantB3;
//			terms[2 * i] = new Compound(new Polynomial(1, new double[] { 0, a[i] }), 
//	     					new Compound(new Cos(), 
//				        	new Polynomial(1, new double[] { 0, i * Math.PI / L })));
        	
        	double constantC1 = i * Math.PI / L * point;
        	double constantC2 = Math.sin(constantC1);
        	double constantC3 = b[i] * constantC2;
        	terms[2 * i + 1] = constantC3;
//			terms[2 * i + 1] = new Compound(new Polynomial(1, new double[] { 0, b[i] }), 
//					        	new Compound(new Sin(), 
//					        	new Polynomial(1, new double[] { 0, i * Math.PI / L })));
        }
        double result = 0;
        for(int i = 0; i < terms.length; i++) {
        	result += terms[i];
        }
        return result;
//		return new Sum(terms);
    }

    public static void main (String[] args){
    	GRFourier gf = new GRFourier();
        Function f = createFunction();
        for(int i = 0; i<10; i++) {
        	double resutl = gf.solve(f, 10, 10, Math.PI, i);
        	System.out.println(resutl);
        }
    }
    
    /**
     * @return Funcion X^2 
     */
    private static Function createFunction() {
    	return new Function() {

			@Override
			public double eval(double x) {
				return x+2;
			}
    	};
    }
}
 