package exceptions;

/**
 * Exception die gethrowt wordt indien de speler nog geen moeilijk spel kan spelen.
 * 
 */
public class MoeilijkSpelToegangException extends IllegalArgumentException {
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public MoeilijkSpelToegangException() {
        super("moeilijkSpelToegangException");
    }
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public MoeilijkSpelToegangException(String message) {
        super(message);
    }
    
}
