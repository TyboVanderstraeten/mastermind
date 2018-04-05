package exceptions;

import java.util.ResourceBundle;

public class MeldAanRegistreerKeuzeException extends IllegalArgumentException {

    public MeldAanRegistreerKeuzeException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("meldAanRegistreerKeuzeException"));
    }

    public MeldAanRegistreerKeuzeException(String message) {
        super(message);
    }

}
