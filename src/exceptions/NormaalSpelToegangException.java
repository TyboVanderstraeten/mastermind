package exceptions;

/**
 * Exception die gethrowt wordt indien de speler nog geen normaal spel kan spelen.
 * 
 */
public class NormaalSpelToegangException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public NormaalSpelToegangException() {
        super("normaalSpelToegangException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public NormaalSpelToegangException(String message) {
        super(message);
    }

}
