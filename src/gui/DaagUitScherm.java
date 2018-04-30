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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import static javafx.scene.layout.GridPane.setHalignment;

//EXCEPTIONS DONE
public class DaagUitScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final String naamUitdagingenCategorie;
    private final int aantalGewonnenCategorie;
    private final KeuzeScherm keuzeScherm;
    private static final ObservableList dataTegenspelers = FXCollections.observableArrayList();

    public DaagUitScherm(DomeinController dc, ResourceBundle resourceBundle, String naamUitdagingenCategorie, int aantalGewonnenCategorie, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.naamUitdagingenCategorie = naamUitdagingenCategorie;
        this.aantalGewonnenCategorie = aantalGewonnenCategorie;
        this.keuzeScherm = keuzeScherm;
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

        Button btnTerug = new Button(resourceBundle.getString("terug"));
        this.add(btnTerug, 0, 2);
        setHalignment(btnTerug, HPos.LEFT);

        Button btnDaagUit = new Button(resourceBundle.getString("uitdagen"));
        this.add(btnDaagUit, 0, 2);
        setHalignment(btnDaagUit, HPos.RIGHT);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        btnTerug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                stage.setScene(keuzeScherm.getScene());
                stage.setTitle("Mastermind");
                dataTegenspelers.clear();
            }
        });

        btnDaagUit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.registreerUitdaging(lvTegenspelers.getSelectionModel().getSelectedItem());
                dc.laadUitdaging(dc.geefSpelersnaam());

                Alert alertUitgedaagd = new Alert(Alert.AlertType.INFORMATION);
                alertUitgedaagd.setTitle(resourceBundle.getString("uitdagen"));
                alertUitgedaagd.setHeaderText(resourceBundle.getString("uitdagenSuccesvol"));
                alertUitgedaagd.setContentText(resourceBundle.getString("uitdagenGelukt"));
                alertUitgedaagd.showAndWait();

                Stage stage = (Stage) (getScene().getWindow());
                SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle, keuzeScherm);
                stage.setScene(new Scene(spelbordScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });
    }
}
