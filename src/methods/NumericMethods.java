package methods;

import exceptions.RootNotFoundException;

public class NumericMethods {
	
	public double bisection(Function f, double a, double b, double error, int iterations) 
		throws RootNotFoundException
	{
		return Bisection.findRoot(f, a, b, error, iterations);
	}

}
