package gui;

import javafx.scene.layout.GridPane;
import domein.DomeinController;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//EXCEPTIONS NOG NIET DONE
public class DaagUitMoeilijkheidsgraadScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;

    public DaagUitMoeilijkheidsgraadScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {
        Button btnMakkelijk = new Button(resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
        this.add(btnMakkelijk, 0, 0);

        Button btnNormaal = new Button(resourceBundle.getString("normaleMoeilijkheidsgraad"));
        this.add(btnNormaal, 1, 0);

        Button btnMoeilijk = new Button(resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
        this.add(btnMoeilijk, 2, 0);

        Label lblError = new Label();
        this.add(lblError, 0, 1, 3, 2);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //Eventhandling
        btnMakkelijk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.kiesMoeilijkheidsgraadUitdagingen(1);
                Stage stage = (Stage) (getScene().getWindow());
                DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, "aantalGewonnenUitdagingenMakkelijk", 0, keuzeScherm);
                stage.setScene(new Scene(daagUitScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });

        btnNormaal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.kiesMoeilijkheidsgraadUitdagingen(2);
                Stage stage = (Stage) (getScene().getWindow());
                DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, "aantalGewonnenUitdagingenMakkelijk", 20, keuzeScherm);
                stage.setScene(new Scene(daagUitScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });

        btnMoeilijk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.kiesMoeilijkheidsgraadUitdagingen(3);
                Stage stage = (Stage) (getScene().getWindow());
                DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, "aantalGewonnenUitdagingenNormaal", 20, keuzeScherm);
                stage.setScene(new Scene(daagUitScherm, 1280, 720));
                stage.setTitle("Mastermind");

            }
        });
    }
}
