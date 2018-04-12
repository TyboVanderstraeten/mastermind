package exceptions;

public class MoeilijkUitdagingToegangException extends IllegalArgumentException {

    public MoeilijkUitdagingToegangException() {
        super("moeilijkUitdagingToegangException");
    }

    public MoeilijkUitdagingToegangException(String message) {
        super(message);
    }

}
