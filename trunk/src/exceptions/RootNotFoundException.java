package exceptions;

public class RootNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2987036985370625806L;

	public RootNotFoundException(String methodName) {
        super(methodName + ": Root not found");
    }
}
