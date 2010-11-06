package methods;

import exceptions.RaizNoEncontradaExcepcion;

public class NumericMethods {
	
	public double bisection(Funcion f, double a, double b, double error, int iterations) 
		throws RaizNoEncontradaExcepcion
	{
		return Biseccion.findRoot(f, a, b, error, iterations);
	}
	
	public double newtonRaphson(Funcion f, Funcion derivedf, double p0, double error, int iterations) 
		throws RaizNoEncontradaExcepcion{
		return NewtonRaphson.findRoot(f, derivedf, p0, error, iterations);
	}
	
	public double romberg(Funcion f, double a, double b, int k){
		return Romberg.romberg(f, a, b, k);
	}

}
