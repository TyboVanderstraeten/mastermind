package exceptions;

import java.util.ResourceBundle;

public class SpelersnaamException extends IllegalArgumentException {

    public SpelersnaamException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("spelersnaamException"));
    }

    public SpelersnaamException(String message) {
        super(message);
    }

}
