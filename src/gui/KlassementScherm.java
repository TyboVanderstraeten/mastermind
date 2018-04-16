package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import gui.KeuzeScherm;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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

        ListView<String> lvKlassementMakkelijk = new ListView<>();
        lvKlassementMakkelijk.setPrefSize(400, 200);
        this.add(lvKlassementMakkelijk, 0, 0);

        ListView<String> lvKlassementNormaal = new ListView<>();
        lvKlassementMakkelijk.setPrefSize(400, 200);
        this.add(lvKlassementNormaal, 1, 0);

        ListView<String> lvKlassementMoeilijk = new ListView<>();
        lvKlassementMakkelijk.setPrefSize(400, 200);
        this.add(lvKlassementMoeilijk, 2, 0);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

    }

}
