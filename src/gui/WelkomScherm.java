package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;

//EXCEPTIONS DONE
/**
 * Het welkomscherm waar men kan aanmelden / registreren + het verloop hiervoor.
 * 
 */
public class WelkomScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    /**
     * Class constructor.
     * Initialiseert dc en resourceBundle.
     * 
     * @param dc instantie van DomeinController.
     * @param resourceBundle instantie van ResourceBundle.
     */
    public WelkomScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        buildGui();
    }

    /**
     * Bevat het volledige verloop voor het "WelkomScherm".
     */
    private void buildGui() {

        Button btnMeldAan = new Button(resourceBundle.getString("meldAan"));
        this.add(btnMeldAan, 0, 0);

        Button btnRegistreer = new Button(resourceBundle.getString("registreer"));
        this.add(btnRegistreer, 1, 0);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                LoginScherm loginScherm = new LoginScherm(dc, resourceBundle, WelkomScherm.this);
                stage.setScene(new Scene(loginScherm, 1280, 720));
                stage.setTitle("Mastermind");

            }
        });

        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                RegistreerScherm registreerScherm = new RegistreerScherm(dc, resourceBundle, WelkomScherm.this);
                stage.setScene(new Scene(registreerScherm, 1280, 720));
                stage.setTitle("Mastermind");

            }
        });
    }

}
