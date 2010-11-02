package exceptions;

public class NotRatedPointException extends Exception{

	private static final long serialVersionUID = -4292290034071057013L;

	public NotRatedPointException(String methodName) {
        super(methodName + ": Root not found");
    }
}
