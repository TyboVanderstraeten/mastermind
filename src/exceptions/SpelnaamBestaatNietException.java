package exceptions;

public class SpelnaamBestaatNietException extends NullPointerException {

    public SpelnaamBestaatNietException() {
        super("spelnaamBestaatNietException");
    }

    public SpelnaamBestaatNietException(String message) {
        super(message);
    }
}
