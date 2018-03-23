package exceptions;

public class SpelerNietUniekException extends RuntimeException{
    
    public SpelerNietUniekException(){
        super("Speler bestaat al!");
    }
    
    public SpelerNietUniekException(String message){
        super(message);
    }

}
