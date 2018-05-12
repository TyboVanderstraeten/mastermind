package exceptions;

/**
 * Exception die gethrowt wordt indien de speler een spelersnaam geeft ingegeven die niet uniek is bij het registreren.
 * 
 */
public class SpelerNietUniekException extends RuntimeException{
    
    /**
     * Class constructor.
     * Roept de superklasse (RunTimeException) aan met defaultmessage.
     */
    public SpelerNietUniekException(){
        super("spelerNietUniekException");
    }
    
    /**
     * Class constructor.
     * Roept de superklasse (RuntimeException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public SpelerNietUniekException(String message){
        super(message);
    }

}
