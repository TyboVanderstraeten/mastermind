package exceptions;

import java.util.ResourceBundle;

public class SpelersnaamWachtwoordCombinatieException extends NullPointerException {

    public SpelersnaamWachtwoordCombinatieException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("spelersnaamWachtwoordCombinatieException"));
    }

    public SpelersnaamWachtwoordCombinatieException(String message) {
        super(message);
    }
}
