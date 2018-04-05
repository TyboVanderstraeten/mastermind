package exceptions;

import java.util.ResourceBundle;

public class MoeilijkSpelToegangException extends IllegalArgumentException {
    
    public MoeilijkSpelToegangException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("moeilijkSpelToegangException"));
    }
    
    public MoeilijkSpelToegangException(String message) {
        super(message);
    }
    
}
