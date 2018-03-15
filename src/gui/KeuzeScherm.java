package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class KeuzeScherm extends GridPane {
    
    public KeuzeScherm(){
        Button btnStartMastermind = new Button("Start Mastermind");
        this.add(this, REMAINING, REMAINING);
        Button btnLaadSpel = new Button("Laad spel");
        
        Button btnDaagUit = new Button("Daag uit");
        
        Button btnAanvaardUitdaging = new Button("Aanvaard uitdaging");
        
        Button btnKlassement = new Button("Klassement");
    }

}
