package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.stage.Stage;

//EXCEPTIONS DONE
/**
 * Het KlassementScherm met de klassementen per moeilijkheidsgraad met het verloop hiervoor.
 * 
 * 
 */
public class KlassementScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
//    private static final ObservableList dataMakkelijk = FXCollections.observableArrayList();
//    private static final ObservableList dataNormaal = FXCollections.observableArrayList();
//    private static final ObservableList dataMoeilijk = FXCollections.observableArrayList();

    /**
     * Class constructor.
     * initialiseert de attributen dc, resourceBundle en keuzeScherm.
     * 
     * @param dc instantie van DomeinController.
     * @param resourceBundle instantie van ResourceBundle.
     * @param keuzeScherm instantie van KeuzeScherm. (vorig scherm)
     */
    public KlassementScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    /**
     * Bevat het volledige verloop van het "KlassementScherm"
     */
    private void buildGui() {
        //Makkelijk
        Label lblMakkelijk = new Label(resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
        this.add(lblMakkelijk, 0, 0);
        Label lblNormaal = new Label(resourceBundle.getString("normaleMoeilijkheidsgraad"));
        this.add(lblNormaal, 1, 0);
        Label lblMoeilijk = new Label(resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
        this.add(lblMoeilijk, 2, 0);
        List<List<String[]>> klassementen = dc.geefKlassement();
        int klassementNr = 0;

        for (List<String[]> klassement : klassementen) {

            ObservableList lijst = FXCollections.observableArrayList();
            for (int teller = 0; teller < klassement.size(); teller++) {
                int index = teller + 1;           
                lijst.add(index + ") " + klassement.get(teller)[0] + " | " + klassement.get(teller)[1]);                
            }
            ListView<String> lvKlassement = new ListView<>(lijst);
            lvKlassement.setPrefSize(400, 600);
            this.add(lvKlassement, klassementNr, 1);
            klassementNr++;
        }


//        for (int teller = 0; teller < klassementen.get(0).size(); teller++) {
//            int index = teller + 1;
//            dataMakkelijk.add(index + ") " + klassementen.get(0).get(teller)[0] + " | " + klassementen.get(0).get(teller)[1]);
//        }
//
//        ListView<String> lvKlassementMakkelijk = new ListView<>();
//        lvKlassementMakkelijk.setPrefSize(400, 600);
//
//        lvKlassementMakkelijk.setItems(dataMakkelijk);
//        this.add(lvKlassementMakkelijk, 0, 1);
//
//        Normaal
//        Label lblNormaal = new Label(resourceBundle.getString("normaleMoeilijkheidsgraad"));
//        this.add(lblNormaal, 1, 0);
//
//        for (int teller = 0; teller < klassementen.get(1).size(); teller++) {
//            int index = teller + 1;
//            dataNormaal.add(index + ") " + klassementen.get(1).get(teller)[0] + " | " + klassementen.get(1).get(teller)[1]);
//        }
//
//        ListView<String> lvKlassementNormaal = new ListView<>();
//        lvKlassementNormaal.setPrefSize(400, 600);
//
//        lvKlassementNormaal.setItems(dataNormaal);
//        this.add(lvKlassementNormaal, 1, 1);
//
//        Moeilijk
//        Label lblMoeilijk = new Label(resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
//        this.add(lblMoeilijk, 2, 0);
//
//        for (int teller = 0; teller < klassementen.get(2).size(); teller++) {
//            int index = teller + 1;
//            dataMoeilijk.add(index + ") " + klassementen.get(2).get(teller)[0] + " | " + klassementen.get(2).get(teller)[1]);
//        }
//
//        ListView<String> lvKlassementMoeilijk = new ListView<>();
//        lvKlassementMoeilijk.setPrefSize(400, 600);
//
//        lvKlassementMoeilijk.setItems(dataMoeilijk);
//        this.add(lvKlassementMoeilijk, 2, 1);

        Button btnTerug = new Button(resourceBundle.getString("terug"));
        this.add(btnTerug, 2, 2);
        setHalignment(btnTerug, HPos.RIGHT);

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
//                dataMakkelijk.clear();
//                dataNormaal.clear();
//                dataMoeilijk.clear();
            }
        });

    }

}
