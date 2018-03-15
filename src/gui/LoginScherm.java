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

public class LoginScherm extends GridPane {

    public LoginScherm() {
        Label lblMeldAan = new Label("Meld aan");
        this.add(lblMeldAan, 0, 0, 2, 1);

        Label lblGebruikersnaam = new Label("Gebruikersnaam:");
        this.add(lblGebruikersnaam, 0, 1);

        Label lblWachtwoord = new Label("Wachtwoord:");
        this.add(lblWachtwoord, 0, 2);

        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);

        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord, 1, 2);

        Button btnMeldAan = new Button("Meld aan");
        this.add(btnMeldAan, 0, 3);
        setHalignment(btnMeldAan, HPos.LEFT);

        Button btnAnnuleer = new Button("Annuleer");
        this.add(btnAnnuleer, 1, 3);
        setHalignment(btnAnnuleer, HPos.RIGHT);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        //Meldaan knop meldt de speler aan!
        //NOG CHECKEN OF WEL DEGELIJK AANMELD
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DomeinController domeinController = new DomeinController();
                domeinController.meldAan(txfGebruikersnaam.getText(), pwfWachtwoord.getText());
                Alert alertAangemeld = new Alert(AlertType.INFORMATION);
                alertAangemeld.setTitle("Aanmelden");
                alertAangemeld.setHeaderText("Aanmelden succesvol");
                alertAangemeld.setContentText(String.format("Speler '%s' is succesvol aangemeld!", domeinController.geefSpelersnaam()));
                alertAangemeld.showAndWait();

            }
        });

        //Annuleerknop sluit venster en gaat terug naar welkomscherm
        //Sluit loginscherm nog niet af?!
        btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                WelkomScherm welkomScherm = new WelkomScherm();
                stage.setScene(new Scene(welkomScherm, 300, 200));
                stage.setTitle("Mastermind");
                stage.show();
            }
        });

    }
}
