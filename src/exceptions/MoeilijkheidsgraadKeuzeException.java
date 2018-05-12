package exceptions;

/**
 * Exception die gethrowt wordt indien de speler een ongeldige moeilijkheidsgraad ingeeft.
 * 
 */
public class MoeilijkheidsgraadKeuzeException extends IllegalArgumentException {
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public MoeilijkheidsgraadKeuzeException(){
        super("moeilijkheidsgraadKeuzeException");
    }
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public MoeilijkheidsgraadKeuzeException(String message){
        super(message);
    }

}
