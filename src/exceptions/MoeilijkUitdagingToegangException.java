package exceptions;

/**
 * Exception die gethrowt wordt indien de speler nog geen moeilijke uitdaging kan spelen.
 * 
 */
public class MoeilijkUitdagingToegangException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public MoeilijkUitdagingToegangException() {
        super("moeilijkUitdagingToegangException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public MoeilijkUitdagingToegangException(String message) {
        super(message);
    }

}
