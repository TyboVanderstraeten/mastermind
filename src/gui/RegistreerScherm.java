package gui;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
        
    }
    
}
