package gui;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import domein.DomeinController;

public class AanvaardUitdagingScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    public AanvaardUitdagingScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
    }
}
