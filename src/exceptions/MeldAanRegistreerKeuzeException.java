package exceptions;

public class MeldAanRegistreerKeuzeException extends IllegalArgumentException {

    public MeldAanRegistreerKeuzeException() {
        super("meldAanRegistreerKeuzeException");
    }

    public MeldAanRegistreerKeuzeException(String message) {
        super(message);
    }

}
