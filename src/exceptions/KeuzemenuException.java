package exceptions;

public class KeuzemenuException extends IllegalArgumentException {

    public KeuzemenuException() {
        super("keuzemenuException");
    }

    public KeuzemenuException(String message) {
        super(message);
    }

}
