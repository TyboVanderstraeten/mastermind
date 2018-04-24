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
import static javafx.scene.layout.GridPane.setHalignment;
import javafx.stage.Stage;
import domein.DomeinController;
import javafx.scene.control.Alert;
import java.util.Locale;
import java.util.ResourceBundle;

//EXCEPTIONS DONE
public class RegistreerScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final WelkomScherm welkomScherm;

    public RegistreerScherm(DomeinController dc, ResourceBundle resourceBundle, WelkomScherm welkomScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.welkomScherm = welkomScherm;
        buildGui();
    }

    private void buildGui() {

        Label lblRegistreer = new Label(resourceBundle.getString("registreer"));
        this.add(lblRegistreer, 0, 0, 2, 1);

        Label lblGebruikersnaam = new Label(resourceBundle.getString("gebruiker"));
        this.add(lblGebruikersnaam, 0, 1);

        Label lblWachtwoord = new Label(resourceBundle.getString("wachtwoord"));
        this.add(lblWachtwoord, 0, 2);

        Label lblWachtwoordBevestiging = new Label(resourceBundle.getString("wachtwoordHerhaling"));
        this.add(lblWachtwoordBevestiging, 0, 3);

        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);

        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord, 1, 2);

        PasswordField pwfWachtwoordBevestiging = new PasswordField();
        this.add(pwfWachtwoordBevestiging, 1, 3);

        Label lblError = new Label();
        this.add(lblError, 0, 4, 2, 1);

        Button btnRegistreer = new Button(resourceBundle.getString("registreer"));
        this.add(btnRegistreer, 0, 5);
        setHalignment(btnRegistreer, HPos.LEFT);

        Button btnAnnuleer = new Button(resourceBundle.getString("annulatie"));
        this.add(btnAnnuleer, 1, 5);
        setHalignment(btnAnnuleer, HPos.RIGHT);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //Eventhandling
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dc.registreer(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), pwfWachtwoordBevestiging.getText());

                    Alert alertGeregistreerd = new Alert(Alert.AlertType.INFORMATION);
                    alertGeregistreerd.setTitle(resourceBundle.getString("registreren"));
                    alertGeregistreerd.setHeaderText(resourceBundle.getString("geslaagdeRegistratie"));
                    alertGeregistreerd.setContentText(resourceBundle.getString("registratieSuccesvol"));
                    alertGeregistreerd.showAndWait();

                    Stage stage = (Stage) (getScene().getWindow());
                    KeuzeScherm keuzeScherm = new KeuzeScherm(dc, resourceBundle);
                    stage.setScene(new Scene(keuzeScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } catch (IllegalArgumentException e) {
                    lblError.setText(resourceBundle.getString(e.getMessage()));
                    txfGebruikersnaam.clear();
                    pwfWachtwoord.clear();
                    pwfWachtwoordBevestiging.clear();
                }
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
