package exceptions;

import java.util.ResourceBundle;

public class NormaalSpelToegangException extends IllegalArgumentException {

    public NormaalSpelToegangException(ResourceBundle resourceBundle) {
        super(resourceBundle.getString("normaalSpelToegangException"));
    }

    public NormaalSpelToegangException(String message) {
        super(message);
    }

}
