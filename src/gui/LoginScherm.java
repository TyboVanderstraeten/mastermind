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

        Label lblMessage = new Label("");
        this.add(lblMessage, 0, 4, 2, 1);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        /*Om een soort van 'tooltip' te geven bij gebruikersnaam, wachtwoord
        WERKT NIET!! 
        Indien werkt = invoeren bij registreren!*/
//        if (txfGebruikersnaam.isFocused()) {
//            lblMessage.setText("Gebruikersnaam bevat max. 40 karakters");
//        }
        //EventHandling
        //Meldaan knop meldt de speler aan!
        //NOG AAN TE VULLEN!!
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        //Annuleerknop sluit venster en gaat terug naar welkomscherm
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
