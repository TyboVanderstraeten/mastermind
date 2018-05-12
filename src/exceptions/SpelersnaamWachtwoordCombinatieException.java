package exceptions;

/**
 * Deze exception wordt gethrowt indien de combinatie spelersnaam - wachtwoord niet klopt.
 * 
 */
public class SpelersnaamWachtwoordCombinatieException extends NullPointerException {

    /**
     * Class constructor.
     * Roept de superklasse (NullPointerException) aan met defaultmessage.
     */
    public SpelersnaamWachtwoordCombinatieException() {
        super("spelersnaamWachtwoordCombinatieException");
    }

    /**
     * Class constructor.
     * Roept de superklasse (NullPointerException) aan met de meegegeven message.
     * @param message message die meegegeven werd en de exception moet beschrijven.
     */
    public SpelersnaamWachtwoordCombinatieException(String message) {
        super(message);
    }
}
