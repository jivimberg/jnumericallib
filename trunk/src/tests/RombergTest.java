package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import methods.Function;
import methods.Romberg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class RombergTest {

	private Function function;
	private String functionString;
	private double a;
	private double b;
	private int k;
	private double expectedResult;
	
	public RombergTest (Function function, String functionString,
			double a, double b, int k, double expectedResult) {
		super();
		this.function = function;
		this.functionString = functionString;
		this.a = a;
		this.b = b;
		this.k = k;
		this.expectedResult = expectedResult;
	}

	@Parameters
    public static Collection<Object[]> data() {
    	//Function 1
    	final Function f1 = new Function(){
            public double eval(double x){
            	return 1 / x;
            }
        };
        final String f1String = "1 / x";
        double expectedResult = 0.69314718191;
        
        //Function 2
    	final Function f2 = new Function(){
            public double eval(double x){
            	return Math.pow(Math.E, Math.pow(x, 2));
            }
        };
        final String f2String = "e^(x^2)";
        double expectedResult2 = 1.462653593;
        
        Object[][] parameters = new Object[][] {{f1, f1String, 1, 2, 16, expectedResult},
        										{f2, f2String, 0, 1, 16, expectedResult2}};    
    	return Arrays.asList(parameters);
    }
	
	@Test
	public void calculateTest(){
		double calculateRoot =  Romberg.romberg(function, a, b, k);
		System.out.println("Function: " + functionString + "\n romberg: " + calculateRoot + " Expected value: " + expectedResult);
		assertEquals("The root founded was not the expected", expectedResult, calculateRoot, 0.00001);
	}
}
