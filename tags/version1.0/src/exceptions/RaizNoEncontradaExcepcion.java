package exceptions;

public class RaizNoEncontradaExcepcion extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2987036985370625806L;

	public RaizNoEncontradaExcepcion(String methodName) {
        super(methodName + ": Root not found");
    }
}
