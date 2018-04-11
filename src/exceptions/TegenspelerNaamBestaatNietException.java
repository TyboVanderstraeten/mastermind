package exceptions;

public class TegenspelerNaamBestaatNietException extends NullPointerException {

    public TegenspelerNaamBestaatNietException() {
        super("tegenspelerNaamBestaatNietException");
    }

    public TegenspelerNaamBestaatNietException(String message) {
        super(message);
    }

}
