package gui;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.stage.Stage;

//EXCEPTIONS DONE
/**
 * Het scherm om een uitdaging te aanvaarden met het verloop hiervoor.
 * 
 */
public class AanvaardUitdagingScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
    private static final ObservableList dataUitdagingen = FXCollections.observableArrayList();

    /**
     * Class constructor.
     * Initialiseert de domeinController, resourcebundle en het vorige scherm (keuzeScherm).
     * @param dc instantie van de DomeinController.
     * @param resourceBundle instantie van de ResourceBundle.
     * @param keuzeScherm instantie van het KeuzeScherm.
     */
    public AanvaardUitdagingScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    /**
     * Bevat het volledige verloop van het aanvaardScherm.
     */
    private void buildGui() {

        Label lblKiesUitdaging = new Label(resourceBundle.getString("selecteerUitdaging"));
        this.add(lblKiesUitdaging, 0, 0);

        for (int teller = 0; teller < dc.geefUitdaging().length; teller++) {
            dataUitdagingen.add(dc.geefUitdaging()[teller][0]);
        }

        ListView<String> lvTegenspelers = new ListView<>();
        lvTegenspelers.setPrefSize(400, 600);
        lvTegenspelers.setItems(dataUitdagingen);
        this.add(lvTegenspelers, 0, 1);

        Button btnTerug = new Button(resourceBundle.getString("terug"));
        this.add(btnTerug, 0, 2);
        setHalignment(btnTerug, HPos.LEFT);

        Button btnAanvaard = new Button(resourceBundle.getString("aanvaard"));
        this.add(btnAanvaard, 0, 2);
        setHalignment(btnAanvaard, HPos.RIGHT);

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
                dataUitdagingen.clear();
            }
        });

        btnAanvaard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.laadUitdaging(lvTegenspelers.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) (getScene().getWindow());
                SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle, keuzeScherm);
                stage.setScene(new Scene(spelbordScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });
    }
}
