package exceptions;

import java.util.ResourceBundle;

public class WachtwoordException extends IllegalArgumentException{

    public WachtwoordException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("wachtwoordException"));
    }

    public WachtwoordException(String message) {
        super(message);
    }
    
    

}
