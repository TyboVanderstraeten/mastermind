package exceptions;

/**
 * Exception die optreedt indien er een foute keuze is ingegeven bij het keuzemenu.
 * 
 */
public class KeuzemenuException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public KeuzemenuException() {
        super("keuzemenuException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public KeuzemenuException(String message) {
        super(message);
    }

}
