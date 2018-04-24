package gui;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import domein.DomeinController;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import static javafx.scene.layout.GridPane.setHalignment;

public class DaagUitScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final String naamUitdagingenCategorie;
    private final int aantalGewonnenCategorie;
    private static final ObservableList dataTegenspelers = FXCollections.observableArrayList();

    public DaagUitScherm(DomeinController dc, ResourceBundle resourceBundle, String naamUitdagingenCategorie, int aantalGewonnenCategorie) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.naamUitdagingenCategorie = naamUitdagingenCategorie;
        this.aantalGewonnenCategorie = aantalGewonnenCategorie;
        buildGui();
    }

    private void buildGui() {

        Label lblKiesTegenspeler = new Label(resourceBundle.getString("selecteerTegenspeler"));
        this.add(lblKiesTegenspeler, 0, 0);

        /*Gewone manier
        for (int teller = 0; teller < dc.geefTegenSpelers(naamUitdagingenCategorie, aantalGewonnenCategorie).length; teller++) {
        dataTegenspelers.add(dc.geefTegenSpelers(naamUitdagingenCategorie, aantalGewonnenCategorie)[teller]);
        }*/
        /*Enhanced for manier
        for (String geefTegenSpeler : dc.geefTegenSpelers(naamUitdagingenCategorie, aantalGewonnenCategorie)) {
        dataTegenspelers.add(geefTegenSpeler);
        }*/
        //Collection manier
        dataTegenspelers.addAll(Arrays.asList(dc.geefTegenSpelers(naamUitdagingenCategorie, aantalGewonnenCategorie)));

        ListView<String> lvTegenspelers = new ListView<>();
        lvTegenspelers.setPrefSize(400, 600);
        lvTegenspelers.setItems(dataTegenspelers);
        this.add(lvTegenspelers, 0, 1);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

    }
}
