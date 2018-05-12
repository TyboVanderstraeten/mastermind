package exceptions;

/**
 * Exception die gethrowt wordt indien de ingegeven tegenspeler niet bestaat.
 * 
 */
public class TegenspelerNaamBestaatNietException extends NullPointerException {

    /**
     * Class constructor.
     * Roept de superklasse (NullPointerException) aan met defaultmessage.
     */
    public TegenspelerNaamBestaatNietException() {
        super("tegenspelerNaamBestaatNietException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (NullPointerException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public TegenspelerNaamBestaatNietException(String message) {
        super(message);
    }

}
