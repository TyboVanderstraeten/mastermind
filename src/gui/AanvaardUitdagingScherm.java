package gui;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.stage.Stage;

public class AanvaardUitdagingScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
    private static final ObservableList dataUitdagingen = FXCollections.observableArrayList();

    public AanvaardUitdagingScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {

        Label lblKiesUitdaging = new Label(resourceBundle.getString("selecteerUitdaging"));
        this.add(lblKiesUitdaging, 0, 0);

        dataUitdagingen.addAll(Arrays.asList(dc.geefUitdaging()));

        ListView<String> lvTegenspelers = new ListView<>();
        lvTegenspelers.setPrefSize(400, 600);
        lvTegenspelers.setItems(dataUitdagingen);
        this.add(lvTegenspelers, 0, 1);

        Button btnTerug = new Button(resourceBundle.getString("terug"));
        this.add(btnTerug, 2, 2);
        setHalignment(btnTerug, HPos.LEFT);

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
    }
}
