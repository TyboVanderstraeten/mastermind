package exceptions;

public class MoeilijkSpelToegangException extends IllegalArgumentException {

    public MoeilijkSpelToegangException() {
        super("Deze mogelijkheidsgraad is pas toegankelijk als u 20 normale spellen heeft gewonnen.");
    }

    public MoeilijkSpelToegangException(String message) {
        super(message);
    }

}
