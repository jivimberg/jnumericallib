package methods;

import exceptions.RootNotFoundException;

public class NumericMethods {
	
	public double bisection(Function f, double a, double b, double error, int iterations) 
		throws RootNotFoundException
	{
		return Bisection.findRoot(f, a, b, error, iterations);
	}
	
	public double newtonRaphson(Function f, Function derivedf, double p0, double error, int iterations) 
		throws RootNotFoundException{
		return NewtonRaphson.findRoot(f, derivedf, p0, error, iterations);
	}
	
	public double romberg(Function f, double a, double b, int k){
		return Romberg.romberg(f, a, b, k);
	}

}
