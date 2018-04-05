package exceptions;

import java.util.ResourceBundle;

public class WachtwoordBevestigingFoutException extends IllegalArgumentException {

    public WachtwoordBevestigingFoutException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("wachtwoordBevestigingFoutException"));
    }

    public WachtwoordBevestigingFoutException(String message) {
        super(message);
    }
    
    

}
