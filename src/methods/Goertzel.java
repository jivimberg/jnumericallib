package methods;

public class Goertzel
{
    public static void goertzel(double y[], double a[], double b[]) {

        int m = y.length - 1;
        final double HM = 2.0/(m+1);

        double s = Math.sin(2 * Math.PI / (m+1));
        double c = Math.cos(2 * Math.PI / (m+1));

        final double ss = s, cc = c;
        for(int j = 1; j <= m; ++j) {
            double u1 = 0, u2 = 0;

            for(int i = m; i >= 1; --i) {
                double u = y[i] + 2*c*u1-u2;
                u2 = u1;
                u1 = u;
            }

            b[j] = HM*u1*s;
            a[j] = HM*(y[0]+u1*c-u2);

            //Actualizo senos y cosenos
            double t = s*cc+c*ss;
            c = c*cc-s*ss;
            s = t;

        }

    }

    private static double truncate(double v)
    {
        return Math.floor(v*100000)/100000;
    }

    public static void main(String[] args)
    {
        Goertzel g = new Goertzel();
        double yr[] = new double[16];
        double yi[] = new double[16];
        double a[] = new double[16];
        double b[] = new double[16];

        for (int i = 0; i < yr.length; i++) {
//            yr[i] = 0.5 - (i%2);
            yr[i] = Math.sin(Math.PI/8.0*i);
//            yr[i] = Math.sin(Math.PI/10.0*i);
        }
        System.out.println("--- Goertzel");
        g.goertzel(yr, a, b);

        for (int i = 1; i < a.length; i++) {
            System.out.println(i + ": " + truncate(a[i]) + ", " + truncate(b[i]));
        }

        System.out.println("--- Inplace FFT");
        fft(1, yr.length, yr, yi);
        for (int i = 1; i < yr.length; i++) {
            System.out.println(i + ": " + truncate(yr[i]/2) + ", " + truncate(yi[i]/2));
        }
    }

    public static void fft(int sign, int n, double ar[], double ai[]) {
        double scale = Math.sqrt(1.0f/n);

        int i,j;
        for (i=j=0; i<n; ++i) {
            if (j>=i) {
                double tempr = ar[j]*scale;
                double tempi = ai[j]*scale;
                ar[j] = ar[i]*scale;
                ai[j] = ai[i]*scale;
                ar[i] = tempr;
                ai[i] = tempi;
            }
            int m = n/2;
            while (m>=1 && j>=m) {
                j -= m;
                m /= 2;
            }
            j += m;
        }

        for (int mmax=1,istep=2*mmax; mmax<n; mmax=istep,istep=2*mmax) {
            double delta = (double)sign*Math.PI/(double)mmax;
            for (int m=0; m<mmax; ++m) {
                double w = (double)m*delta;
                double wr = Math.cos(w);
                double wi = Math.sin(w);
                for (i=m; i<n; i+=istep) {
                    j = i+mmax;
                    double tr = wr*ar[j]-wi*ai[j];
                    double ti = wr*ai[j]+wi*ar[j];
                    ar[j] = ar[i]-tr;
                    ai[j] = ai[i]-ti;
                    ar[i] += tr;
                    ai[i] += ti;
                }
            }
            //noinspection UnusedAssignment
            mmax = istep;
        }
    }



}


