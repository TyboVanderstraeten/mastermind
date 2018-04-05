package exceptions;

import java.util.ResourceBundle;

public class SpelerNietUniekException extends RuntimeException{
    
    public SpelerNietUniekException(ResourceBundle resourceBundle){
        super(resourceBundle.getString("spelerNietUniekException"));
    }
    
    public SpelerNietUniekException(String message){
        super(message);
    }

}
