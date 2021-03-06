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

//EXCEPTIONS DONE
/**
 * Het scherm om de moeilijkheidsgraad te kiezen om een speler uit te dagen met het volledige verloop hiervoor.
 * 
 * 
 */
public class DaagUitMoeilijkheidsgraadScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;

    /**
     * Class constructor.
     * Initialiseert de attributen dc, resourceBundle en keuzeScherm.
     * 
     * @param dc instantie van DomeinController.
     * @param resourceBundle instantie van ResourceBundle.
     * @param keuzeScherm instantie van KeuzeScherm (vorig scherm)
     */
    public DaagUitMoeilijkheidsgraadScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    /**
     * Bevat het volledige verloop voor het "DaagUitMoeilijkheidsgraadScherm"
     */
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
                int[] aantalGewonnen = {0,0};
                DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, aantalGewonnen, keuzeScherm);
                stage.setScene(new Scene(daagUitScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });

        btnNormaal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dc.kiesMoeilijkheidsgraadUitdagingen(2);
                    Stage stage = (Stage) (getScene().getWindow());
                    int[] aantalGewonnen = {20,0};
                    DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, aantalGewonnen, keuzeScherm);
                    stage.setScene(new Scene(daagUitScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } catch (IllegalArgumentException e) {
                    lblError.setText(resourceBundle.getString("normaalUitdagingToegangException"));
                }
            }
        });

        btnMoeilijk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dc.kiesMoeilijkheidsgraadUitdagingen(3);
                    Stage stage = (Stage) (getScene().getWindow());
                    int[] aantalGewonnen = {20,20};
                    DaagUitScherm daagUitScherm = new DaagUitScherm(dc, resourceBundle, aantalGewonnen, keuzeScherm);
                    stage.setScene(new Scene(daagUitScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } catch (IllegalArgumentException e) {
                    lblError.setText(resourceBundle.getString("moeilijkUitdagingToegangException"));
                }

            }
        });
    }
}
