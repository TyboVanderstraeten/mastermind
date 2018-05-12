package exceptions;

/**
 * Exception die optreedt indien er een ongeldige kleur is ingegeven.
 * 
 */
public class FoutKleurException extends IllegalArgumentException {

    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met defaultmessage.
     */
    public FoutKleurException() {
        super("ongeldigeKleur");
    }
    
    /**
     * Class constructor.
     * Roept de superklasse (IllegalArgumentException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public FoutKleurException(String message){
        super(message);
    }
}
