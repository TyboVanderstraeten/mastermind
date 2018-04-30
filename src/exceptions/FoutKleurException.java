package exceptions;

public class FoutKleurException extends IllegalArgumentException {

    public FoutKleurException() {
        super("ongeldigeKleur");
    }
    
    public FoutKleurException(String message){
        super(message);
    }
}
