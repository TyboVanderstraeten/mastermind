package exceptions;

public class WachtwoordException extends IllegalArgumentException{

    public WachtwoordException() {
        super("wachtwoordException");
    }

    public WachtwoordException(String message) {
        super(message);
    }
    
    

}
