package exceptions;

public class WachtwoordException extends IllegalArgumentException{

    public WachtwoordException() {
        super("Wachtwoord moet 8-25 karakters bevatten (grenzen inbegrepen)!");
    }

    public WachtwoordException(String message) {
        super(message);
    }
    
    

}
