package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class LaadSpelScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private static final ObservableList dataSpellen = FXCollections.observableArrayList();

    public LaadSpelScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
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
        
        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
    }

}
