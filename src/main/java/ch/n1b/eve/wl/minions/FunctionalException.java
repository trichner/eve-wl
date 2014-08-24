package ch.n1b.eve.wl.minions;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
public class FunctionalException extends Exception {
    public FunctionalException() {
        super("Functional Exception, something wen't wrong.");
    }

    public FunctionalException(String message) {
        super(message);
    }

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionalException(Throwable cause) {
        super(cause);
    }
}
