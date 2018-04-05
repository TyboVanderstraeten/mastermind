package exceptions;

public class WachtwoordBevestigingFoutException extends IllegalArgumentException {

    public WachtwoordBevestigingFoutException() {
        super("wachtwoordBevestigingFoutException");
    }

    public WachtwoordBevestigingFoutException(String message) {
        super(message);
    }
    
    

}
