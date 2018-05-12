package exceptions;

/**
 * Exception die gethrowt wordt indien de spelersnaam te lang of leeg is.
 * 
 */
public class SpelersnaamException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public SpelersnaamException() {
        super("spelersnaamException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public SpelersnaamException(String message) {
        super(message);
    }

}
