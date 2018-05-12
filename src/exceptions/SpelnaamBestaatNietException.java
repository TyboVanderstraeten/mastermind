package exceptions;

/**
 * Exception die gethrowt wordt indien de spelnaam niet bestaat.
 *
 */
public class SpelnaamBestaatNietException extends NullPointerException {

    /**
     * Class constructor. Roept de superklasse (NullPointerException) aan
     * met defaultmessage.
     */
    public SpelnaamBestaatNietException() {
        super("spelnaamBestaatNietException");
    }

    /**
     * Class constructor. Roept de superklasse (NullPointerException) aan met
     * de meegegeven message.
     *
     * @param message message die meegegeven werd en de exception moet
     * beschrijven.
     */
    public SpelnaamBestaatNietException(String message) {
        super(message);
    }
}
