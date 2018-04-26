package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.stage.Stage;

//EXCEPTIONS DONE
public class LaadSpelScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
    private static final ObservableList dataSpellen = FXCollections.observableArrayList();

    public LaadSpelScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {

        Label lblKiesSpel = new Label(resourceBundle.getString("selecteerSpel"));
        this.add(lblKiesSpel, 0, 0);

        dataSpellen.addAll(Arrays.asList(dc.geefSpellen()));

        ListView<String> lvSpellen = new ListView<>();
        lvSpellen.setPrefSize(400, 600);
        lvSpellen.setItems(dataSpellen);
        this.add(lvSpellen, 0, 1);

        Button btnTerug = new Button(resourceBundle.getString("terug"));
        this.add(btnTerug, 0, 2);
        setHalignment(btnTerug, HPos.LEFT);

        Button btnLaad = new Button(resourceBundle.getString("laad"));
        this.add(btnLaad, 0, 2);
        setHalignment(btnLaad, HPos.RIGHT);

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
                dataSpellen.clear();
            }
        });

        btnLaad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.laadSpel(lvSpellen.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) (getScene().getWindow());
                SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle);
                stage.setScene(new Scene(spelbordScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });
    }

}
