package exceptions;

import java.util.ResourceBundle;

public class TaalKeuzeException extends IllegalArgumentException{
    
    public TaalKeuzeException(ResourceBundle resourceBundle){
        super(resourceBundle.getString("taalKeuzeException"));
    }
    
    public TaalKeuzeException(String message){
        super(message);
    }

}
