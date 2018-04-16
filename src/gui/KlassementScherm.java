package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import gui.KeuzeScherm;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

//EXCEPTIONS DONE
public class KlassementScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
    private static final ObservableList dataMakkelijk = FXCollections.observableArrayList();
    private static final ObservableList dataNormaal = FXCollections.observableArrayList();
    private static final ObservableList dataMoeilijk = FXCollections.observableArrayList();

    public KlassementScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {
        //Makkelijk
        Label lblMakkelijk = new Label(resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
        this.add(lblMakkelijk, 0, 0);

        ListView<String> lvKlassementMakkelijk = new ListView<>();
        lvKlassementMakkelijk.setPrefSize(400, 600);

        for (int i = 0; i < dc.geefKlassementMakkelijk().size(); i++) {
            dataMakkelijk.add(dc.geefKlassementMakkelijk());
        }

        lvKlassementMakkelijk.setItems(dataMakkelijk);
        this.add(lvKlassementMakkelijk, 0, 1);

        //Normaal
        Label lblNormaal = new Label(resourceBundle.getString("normaleMoeilijkheidsgraad"));
        this.add(lblNormaal, 1, 0);

        ListView<String> lvKlassementNormaal = new ListView<>();
        lvKlassementNormaal.setPrefSize(400, 600);

        for (int i = 0; i < dc.geefKlassementNormaal().size(); i++) {
            dataNormaal.add(dc.geefKlassementNormaal());
        }

        this.add(lvKlassementNormaal, 1, 1);

        //Moeilijk
        Label lblMoeilijk = new Label(resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
        this.add(lblMoeilijk, 2, 0);

        ListView<String> lvKlassementMoeilijk = new ListView<>();
        lvKlassementMoeilijk.setPrefSize(400, 600);

        for (int i = 0; i < dc.geefKlassementMoeilijk().size(); i++) {
            dataMoeilijk.add(dc.geefKlassementMoeilijk());
        }

        this.add(lvKlassementMoeilijk, 2, 1);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

    }

}
