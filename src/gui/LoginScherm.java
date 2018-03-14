package gui;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginScherm extends GridPane {
    
    public LoginScherm() {
        Label lblMeldAan = new Label("Meld aan");
        this.add(lblMeldAan, 0, 0, 2, 1);
        
        Label lblGebruikersnaam = new Label("Gebruikersnaam");
        this.add(lblGebruikersnaam,0,1);
        
        Label lblWachtwoord = new Label("Wachtwoord");
        this.add(lblWachtwoord,0,2);
        
        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);
        
        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord,1,2);  
                
    }
}
