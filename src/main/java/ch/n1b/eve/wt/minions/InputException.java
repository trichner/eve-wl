package ch.n1b.eve.wt.minions;

/**
 * Created on 24.08.2014.
 *
 * @author Thomas
 */
public class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputException(Throwable cause) {
        super(cause);
    }
}
