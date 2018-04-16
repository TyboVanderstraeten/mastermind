package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import domein.DomeinController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final WelkomScherm welkomScherm;

    public LoginScherm(DomeinController dc, ResourceBundle resourceBundle, WelkomScherm welkomScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.welkomScherm = welkomScherm;
        buildGui();
    }

    private void buildGui() {
        Label lblMeldAan = new Label(resourceBundle.getString("meldAan"));
        this.add(lblMeldAan, 0, 0, 2, 1);

        Label lblGebruikersnaam = new Label(resourceBundle.getString("gebruiker"));
        this.add(lblGebruikersnaam, 0, 1);

        Label lblWachtwoord = new Label(resourceBundle.getString("wachtwoord"));
        this.add(lblWachtwoord, 0, 2);

        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);

        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord, 1, 2);

        Button btnMeldAan = new Button(resourceBundle.getString("meldAan"));
        this.add(btnMeldAan, 0, 3);
        setHalignment(btnMeldAan, HPos.LEFT);

        Button btnAnnuleer = new Button(resourceBundle.getString("annulatie"));
        this.add(btnAnnuleer, 1, 3);
        setHalignment(btnAnnuleer, HPos.RIGHT);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.meldAan(txfGebruikersnaam.getText(), pwfWachtwoord.getText());
                Alert alertAangemeld = new Alert(AlertType.INFORMATION);
                alertAangemeld.setTitle(resourceBundle.getString("meldAan"));
                alertAangemeld.setHeaderText(resourceBundle.getString("geslaagdeAanmelding"));
                alertAangemeld.setContentText(String.format("%s '%s' %s", resourceBundle.getString("aanmeldingSuccesvolD1"), dc.geefSpelersnaam(), resourceBundle.getString("aanmeldingSuccesvolD2")));
                alertAangemeld.showAndWait();
                
                Stage stage = (Stage)(getScene().getWindow());
                KeuzeScherm keuzeScherm = new KeuzeScherm(dc,resourceBundle);
                stage.setScene(new Scene(keuzeScherm,1280,720));
                stage.setTitle(resourceBundle.getString("menu"));
                

            }
        });

        btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) (getScene().getWindow());
                stage.setScene(welkomScherm.getScene());
                stage.setTitle("Mastermind");
            }
        });
    }
}
