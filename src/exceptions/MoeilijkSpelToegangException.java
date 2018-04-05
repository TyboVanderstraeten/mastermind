package exceptions;

public class MoeilijkSpelToegangException extends IllegalArgumentException {
    
    public MoeilijkSpelToegangException() {
        super("moeilijkSpelToegangException");
    }
    
    public MoeilijkSpelToegangException(String message) {
        super(message);
    }
    
}
