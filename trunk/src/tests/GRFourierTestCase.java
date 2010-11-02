package tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import methods.Function;

@RunWith(value = Parameterized.class)
public class GRFourierTestCase {
	
	private Function function;
	private String functionString;
	private int n;
	private int m;
	private double l;
	
	public GRFourierTestCase(Function function, String functionString, int n, int m, double l) {
		this.function = function;
		this.functionString = functionString;
		this.n = n;
		this.m = m;
		this.l = l;
	}
	
	@Parameters
    public static Collection<Object[]> data() {
    	final Function function = new Function(){
            public double eval(double x){
                return  Math.pow(x,3) + 4 * Math.pow(x,2) - 10;
            }
        };
        final String functionString = "x^3 + 4x^2 - 10";
        
        Object[][] parameters = new Object[][] {{function , functionString, }}; 
    	return Arrays.asList(parameters);
    }

}
