package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class KeuzeScherm extends GridPane {

    public KeuzeScherm() {
        Button btnStartMastermind = new Button("Start Mastermind");
        this.add(btnStartMastermind, 0, 0);

        Button btnLaadSpel = new Button("Laad spel");
        this.add(btnLaadSpel, 1, 0);

        Button btnDaagUit = new Button("Daag uit");
        this.add(btnDaagUit, 2, 0);

        Button btnAanvaardUitdaging = new Button("Aanvaard uitdaging");
        this.add(btnAanvaardUitdaging, 3, 0);

        Button btnKlassement = new Button("Klassement");
        this.add(btnKlassement, 4, 0);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
    }

}
