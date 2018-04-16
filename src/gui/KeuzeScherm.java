package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.Locale;
import java.util.ResourceBundle;

public class KeuzeScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    public KeuzeScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        buildGui();
    }

    private void buildGui() {

        Button btnStartMastermind = new Button(resourceBundle.getString("start"));
        this.add(btnStartMastermind, 0, 0);

        Button btnLaadSpel = new Button(resourceBundle.getString("laad"));
        this.add(btnLaadSpel, 1, 0);

        Button btnDaagUit = new Button(resourceBundle.getString("uitdagen"));
        this.add(btnDaagUit, 2, 0);

        Button btnAanvaardUitdaging = new Button(resourceBundle.getString("aanvaarden"));
        this.add(btnAanvaardUitdaging, 3, 0);

        Button btnKlassement = new Button(resourceBundle.getString("klassement"));
        this.add(btnKlassement, 4, 0);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
    }

}
