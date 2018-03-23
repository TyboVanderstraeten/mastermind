package exceptions;

public class WachtwoordBevestigingFoutException extends IllegalArgumentException {

    public WachtwoordBevestigingFoutException() {
        super("Wachtwoord en wachtwoordbevestiging moeten gelijk zijn!");
    }

    public WachtwoordBevestigingFoutException(String message) {
        super(message);
    }
    
    

}
