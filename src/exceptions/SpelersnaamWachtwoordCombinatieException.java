package exceptions;

public class SpelersnaamWachtwoordCombinatieException extends NullPointerException {

    public SpelersnaamWachtwoordCombinatieException() {
        super("spelersnaamWachtwoordCombinatieException");
    }

    public SpelersnaamWachtwoordCombinatieException(String message) {
        super(message);
    }
}
