package exceptions;

public class TaalKeuzeException extends IllegalArgumentException{
    
    public TaalKeuzeException(){
        super("taalKeuzeException");
    }
    
    public TaalKeuzeException(String message){
        super(message);
    }

}
