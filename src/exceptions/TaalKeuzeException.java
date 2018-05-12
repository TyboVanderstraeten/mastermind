package exceptions;

/**
 * Exception die gethrowt wordt als men een verkeerd nummer heeft ingevoerd (>3) om een taal te kiezen.
 * 
 */
public class TaalKeuzeException extends IllegalArgumentException{
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public TaalKeuzeException(){
        super("taalKeuzeException");
    }
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public TaalKeuzeException(String message){
        super(message);
    }

}
