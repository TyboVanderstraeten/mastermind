package exceptions;

public class TaalKeuzeException extends IllegalArgumentException{
    
    public TaalKeuzeException(){
        super("U moet een keuze maken tussen 1/2/3!");
    }
    
    public TaalKeuzeException(String message){
        super(message);
    }

}
