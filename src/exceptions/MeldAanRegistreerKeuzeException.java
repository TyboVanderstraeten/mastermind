package exceptions;

public class MeldAanRegistreerKeuzeException extends IllegalArgumentException {

    public MeldAanRegistreerKeuzeException() {
        super("U moet een keuze maken tussen 1/2!");
    }

    public MeldAanRegistreerKeuzeException(String message) {
        super(message);
    }

}
