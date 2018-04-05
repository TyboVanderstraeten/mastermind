package exceptions;

public class MoeilijkheidsgraadKeuzeException extends IllegalArgumentException {
    
    public MoeilijkheidsgraadKeuzeException(){
        super("moeilijkheidsgraadKeuzeException");
    }
    
    public MoeilijkheidsgraadKeuzeException(String message){
        super(message);
    }

}
