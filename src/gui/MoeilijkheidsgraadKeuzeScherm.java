package gui;

import domein.DomeinController;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//EXCEPTIONS DONE
/**
 * Het scherm waar men de moeilijkheidsgraad kiest van een gewoon spel + het verloop hiervoor.
 * 
 *
 */
public class MoeilijkheidsgraadKeuzeScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;

    /**
     * Class constructor.
     * Initialiseert de attributen dc, resourceBundle en keuzeScherm.
     * 
     * @param dc Instantie van DomeinController
     * @param resourceBundle Instantie van ResourceBundle
     * @param keuzeScherm Instantie van KeuzeScherm.
     */
    public MoeilijkheidsgraadKeuzeScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    /**
     * Bevat het volledige verloop van het "MoeilijkheidsgraadKeuzeScherm"
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
                dc.kiesMoeilijkheidsgraad(1);
                Stage stage = (Stage) (getScene().getWindow());
                SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle, keuzeScherm);
                stage.setScene(new Scene(spelbordScherm, 1280, 720));
                stage.setTitle("Mastermind");
            }
        });

        btnNormaal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dc.kiesMoeilijkheidsgraad(2);
                    Stage stage = (Stage) (getScene().getWindow());
                    SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle, keuzeScherm);
                    stage.setScene(new Scene(spelbordScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } catch (IllegalArgumentException e) {
                    lblError.setText(resourceBundle.getString("normaalSpelGuiToegangException"));
                }
            }
        });

        btnMoeilijk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dc.kiesMoeilijkheidsgraad(3);
                    Stage stage = (Stage) (getScene().getWindow());
                    SpelbordScherm spelbordScherm = new SpelbordScherm(dc, resourceBundle, keuzeScherm);
                    stage.setScene(new Scene(spelbordScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } catch (IllegalArgumentException e) {
                    lblError.setText(resourceBundle.getString("moeilijkSpelGuiToegangException"));
                }
            }
        });
    }

}
