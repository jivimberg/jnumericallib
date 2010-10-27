package exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: May 25, 2010
 * Time: 4:12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RootNotFoundException extends Exception {
    public RootNotFoundException() {
        super("Root not found");
    }
}
