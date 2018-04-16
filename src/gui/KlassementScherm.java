package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import gui.KeuzeScherm;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

//EXCEPTIONS DONE
public class KlassementScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;

    public KlassementScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {

    }

}
