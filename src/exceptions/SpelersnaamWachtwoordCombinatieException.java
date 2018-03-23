package exceptions;

public class SpelersnaamWachtwoordCombinatieException extends NullPointerException {

    public SpelersnaamWachtwoordCombinatieException() {
        super("Spelersnaam/wachtwoord is incorrect!");
    }

    public SpelersnaamWachtwoordCombinatieException(String message) {
        super(message);
    }
}
