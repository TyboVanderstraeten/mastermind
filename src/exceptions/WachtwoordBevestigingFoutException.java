package exceptions;

/**
 * Exception die gethrowt wordt indien de wachtwoord bevestiging niet overeenkomt met het ingegeven wachtwoord.
 *
 */
public class WachtwoordBevestigingFoutException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public WachtwoordBevestigingFoutException() {
        super("wachtwoordBevestigingFoutException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public WachtwoordBevestigingFoutException(String message) {
        super(message);
    }
    
    

}
