package gui;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelkomScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    public WelkomScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        buildGui();
    }

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
        //Meldaan knop die naar het loginscherm leidt
        //Sluit welkomscherm nog niet af?!
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                LoginScherm loginScherm = new LoginScherm(dc, WelkomScherm.this);
                stage.setScene(new Scene(loginScherm, 300, 200));
                stage.setTitle(resourceBundle.getString("meldAan"));

            }
        });

        //Registreer knop die naar het registreerscherm leidt
        //Sluit welkomscherm nog niet af?!
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                RegistreerScherm registreerScherm = new RegistreerScherm(dc, WelkomScherm.this);
                stage.setScene(new Scene(registreerScherm, 300, 200));
                stage.setTitle(resourceBundle.getString("registreer"));

            }
        });
    }

}
