package exceptions;

/**
 * Exception die gethrowt wordt indien de spelnaam niet uniek is.
 * 
 */
public class SpelnaamNietUniekException extends RuntimeException {

    /**
     * Class constructor.
     * Roept de superklasse (RuntimeException) aan met defaultmessage.
     */
    public SpelnaamNietUniekException() {
        super("spelnaamNietUniekException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (RuntimeException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public SpelnaamNietUniekException(String message) {
        super(message);
    }

}
