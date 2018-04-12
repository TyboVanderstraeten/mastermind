package exceptions;

public class NormaalUitdagingToegangException extends IllegalArgumentException {

    public NormaalUitdagingToegangException() {
        super("normaalUitdagingToegangException");
    }

    public NormaalUitdagingToegangException(String message) {
        super(message);
    }

}
