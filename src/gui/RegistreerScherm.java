package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.setHalignment;

public class RegistreerScherm extends GridPane {
    
    public RegistreerScherm() {
        Label lblRegistreer = new Label("Registreer");
        this.add(lblRegistreer, 0, 0, 2, 1);
        
        Label lblGebruikersnaam = new Label("Gebruikersnaam");
        this.add(lblGebruikersnaam, 0, 1);
        
        Label lblWachtwoord = new Label("Wachtwoord");
        this.add(lblWachtwoord, 0, 2);
        
        Label lblWachtwoordBevestiging = new Label("Bevestig wachtwoord");
        this.add(lblWachtwoordBevestiging, 0, 3);
        
        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);
        
        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord, 1, 2);
        
        PasswordField pwfWachtwoordBevestiging = new PasswordField();
        this.add(pwfWachtwoordBevestiging, 1, 3);
        
        Button btnRegistreer = new Button("Registreer");
        this.add(btnRegistreer, 0, 4);
        setHalignment(btnRegistreer, HPos.LEFT);
        
        Button btnAnnuleer = new Button("Annuleer");
        this.add(btnAnnuleer, 1, 4);
        setHalignment(btnAnnuleer, HPos.RIGHT);
        
        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);      
        
    }
    
}
