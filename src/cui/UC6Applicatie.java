package cui;

import domein.DomeinController;
import java.util.ResourceBundle;

public class UC6Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC6Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    public void start() {

    }
}
