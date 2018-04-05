package exceptions;

public class SpelnaamNietUniekException extends RuntimeException {

    public SpelnaamNietUniekException() {
        super("spelnaamNietUniekException");
    }

    public SpelnaamNietUniekException(String message) {
        super(message);
    }

}
