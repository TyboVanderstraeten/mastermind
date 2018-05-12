package exceptions;

/**
 * Exception die gethrowt wordt indien het wachtwoord een ongeldig formaat heeft.
 * 
 */
public class WachtwoordException extends IllegalArgumentException{

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public WachtwoordException() {
        super("wachtwoordException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public WachtwoordException(String message) {
        super(message);
    }
    
    

}
