package gui;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AanvaardUitdagingScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private static final ObservableList dataUitdagingen = FXCollections.observableArrayList();

    public AanvaardUitdagingScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        buildGui();
    }

    private void buildGui() {

        Label lblKiesUitdaging = new Label(resourceBundle.getString("selecteerUitdaging"));
        this.add(lblKiesUitdaging, 0, 0);

        dataUitdagingen.addAll(Arrays.asList(dc.aanvaardUitdaging()));

        ListView<String> lvTegenspelers = new ListView<>();
        lvTegenspelers.setPrefSize(400, 600);
        lvTegenspelers.setItems(dataUitdagingen);
        this.add(lvTegenspelers, 0, 1);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
    }
}
