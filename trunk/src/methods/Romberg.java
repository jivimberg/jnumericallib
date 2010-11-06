package methods;

public class Romberg {

	 /**
     * Tabula una funcion.
     * Evalua la funcion en el intervalo cerrado [a,b] y devuelve un arreglo con n+1
     * elementos.
     * @param f funcion a ser tabulada
     * @param a comienzo del intervalo
     * @param b fin del intervalo
     * @param n cantidad de intervalos
     * @return la funcion tabulada
     */
    private static double[] tabulate(Funcion f, double a, double b, int n) {
        double[] t = new double[n+1];
        double h = (b-a)/n;
        for (int i = 0; i < n+1; i++) {
            t[i] = f.eval(a+(i*h));
        }
        return t;
    }


    /**
     * Integra por trapecios una funcion tabulada en 2^k intervalos.
     * @param t funcion tabulada
     * @param a comienzo del intervalo
     * @param b fin del intervalo
     * @param k cantidad de intervalos
     * @return la integral calculada por trapecios
     */
    public static double trapecios(double[] t, double a, double b, int k) {
        int n = (int) Math.pow(2,k);
        double h = (b-a)/n;        
        int increment = t.length/n;
        double accum = 0;
        for (int i = increment; i < t.length-1; i+=increment) {
            accum += t[i];
        }
        return (t[0]+2*accum+t[t.length-1])*h/2;
    }

    /**
     * Integra por romberg una funcion tabulada en 2^k intervalos.
     * El tamanño de t debe ser 2^k+1.
     * @param t funcion tabulada
     * @param a comienzo del intervalo
     * @param b fin del intervalo
     * @return la integral calculada
     */
    private static double romberg(double[] t, double a, double b) {
        int v = (int)(Math.log(t.length) / Math.log(2));
        if( Math.abs(Math.pow(2,v)- (t.length-1)) > 0.0001) {
            throw new IllegalArgumentException("" + t.length);
        }
        double[] rn = new double[v];

        //Primera pasada, calcular las diferentes aproximaciones por trapecios.
        for (int i = 0; i < rn.length; i++) {
            rn[i] = trapecios(t, a, b, i);
        }

        //Correccion por romberg.
        for(int k = 1; k < rn.length-1; k++) {
            final double c = Math.pow(4, k);
            int jend = rn.length - k - 1;
            for(int j = 0; j < jend; j++ ) {
                rn[j] = ((c* rn[j+1])- rn[j])/(c-1);
            }
            rn[jend] = Double.NaN; // "limpio" el arreglo para detectar bugs.
        }
        return rn[0];
    }


    /**
     * Integra por romberg una funcion tabulada en 2^k intervalos.
     * @param f Función a integrar
     * @param a comienzo del intervalo
     * @param b fin del intervalo
     * @param k cantidad de intervalos
     * @return la integral calculada
     */
    public static double romberg(Funcion f, double a, double b, int k) {
        int n = 1 << k;
        double[] t = tabulate(f, a, b, n);
        return romberg(t,a, b);
    }

}
