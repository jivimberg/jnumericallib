package exceptions;

public class RootNotFoundException extends Exception {
    public RootNotFoundException() {
        super("Root not found");
    }
}
