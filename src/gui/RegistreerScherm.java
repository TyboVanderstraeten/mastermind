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

public class RegistreerScherm extends GridPane {

    public RegistreerScherm(DomeinController dc, WelkomScherm ws) {
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
//      ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
//      ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);
        
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

        Button btnRegistreer = new Button(resourceBundle.getString("registreer"));
        this.add(btnRegistreer, 0, 4);
        setHalignment(btnRegistreer, HPos.LEFT);

        Button btnAnnuleer = new Button(resourceBundle.getString("annulatie"));
        this.add(btnAnnuleer, 1, 4);
        setHalignment(btnAnnuleer, HPos.RIGHT);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        //Registreerknop registreert de speler!
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DomeinController domeinController = new DomeinController();
                domeinController.registreer(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), pwfWachtwoordBevestiging.getText());
                Alert alertGeregistreerd = new Alert(Alert.AlertType.INFORMATION);
                alertGeregistreerd.setTitle(resourceBundle.getString("registreren"));
                alertGeregistreerd.setHeaderText(resourceBundle.getString("geslaagdeRegistratie"));
                alertGeregistreerd.setContentText(resourceBundle.getString("registratieSuccesvol"));
                alertGeregistreerd.showAndWait();
            }
        });

        //Annuleerknop sluit venster en gaat terug naar welkomscherm
        //Sluit registreerscherm nog niet af?!
        btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)(getScene().getWindow());             
                stage.setScene(ws.getScene());   
                stage.setTitle("Mastermind");
            }
        });

    }

}
