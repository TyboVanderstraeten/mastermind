package exceptions;

public class spelnaamBestaatNietException extends NullPointerException {

    public spelnaamBestaatNietException() {
        super("spelnaamBestaatNietException");
    }

    public spelnaamBestaatNietException(String message) {
        super(message);
    }
}
