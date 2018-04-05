package exceptions;

public class SpelerNietUniekException extends RuntimeException{
    
    public SpelerNietUniekException(){
        super("spelerNietUniekException");
    }
    
    public SpelerNietUniekException(String message){
        super(message);
    }

}
