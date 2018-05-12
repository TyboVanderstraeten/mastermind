package exceptions;

/**
 * Exception die gethrowt wordt indien er een fout keuze is ingegeven bij het keuzemenu om te registreren of aan te melden.
 * 
 */
public class MeldAanRegistreerKeuzeException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public MeldAanRegistreerKeuzeException() {
        super("meldAanRegistreerKeuzeException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public MeldAanRegistreerKeuzeException(String message) {
        super(message);
    }

}
