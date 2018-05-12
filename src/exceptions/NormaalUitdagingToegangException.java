package exceptions;

/**
 * Exception die gethrowt wordt indien de speler nog geen normale uitdaging kan spelen.
 * 
 */
public class NormaalUitdagingToegangException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public NormaalUitdagingToegangException() {
        super("normaalUitdagingToegangException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public NormaalUitdagingToegangException(String message) {
        super(message);
    }

}
