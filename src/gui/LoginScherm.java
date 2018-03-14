package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginScherm extends GridPane {
    
    public LoginScherm() {
        Label lblMeldAan = new Label("Meld aan");
        this.add(lblMeldAan, 0, 0, 2, 1);
        
        Label lblGebruikersnaam = new Label("Gebruikersnaam:");
        this.add(lblGebruikersnaam,0,1);
        
        Label lblWachtwoord = new Label("Wachtwoord:");
        this.add(lblWachtwoord,0,2);
        
        TextField txfGebruikersnaam = new TextField();
        this.add(txfGebruikersnaam, 1, 1);
        
        PasswordField pwfWachtwoord = new PasswordField();
        this.add(pwfWachtwoord,1,2);  
        
        Button btnMeldAan = new Button("Meld aan");
        this.add(btnMeldAan, 0, 3);
        setHalignment(btnMeldAan, HPos.LEFT);
        
        Button btnAnnuleer = new Button("Annuleer");
        this.add(btnAnnuleer, 1, 3);
        setHalignment(btnAnnuleer, HPos.RIGHT);
        
        Label lblMessage = new Label("");
        this.add(lblMessage,0,4,2,1);
        
        if(lblGebruikersnaam.isHover() == true){
            lblMessage.setText("Gebruikersnaam bevat max. 40 karakters");
        }
        
        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);               
    }
}
