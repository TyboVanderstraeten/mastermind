package exceptions;

public class NormaalSpelToegangException extends IllegalArgumentException {

    public NormaalSpelToegangException() {
        super("normaalSpelToegangException");
    }

    public NormaalSpelToegangException(String message) {
        super(message);
    }

}
