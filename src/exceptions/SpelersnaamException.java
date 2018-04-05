package exceptions;

public class SpelersnaamException extends IllegalArgumentException {

    public SpelersnaamException() {
        super("spelersnaamException");
    }

    public SpelersnaamException(String message) {
        super(message);
    }

}
