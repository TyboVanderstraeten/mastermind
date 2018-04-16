package gui;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TaalScherm extends GridPane {

    public TaalScherm() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
        //     ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
        //     ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);
        
        Label lblMastermind = new Label("Mastermind");
        this.add(lblMastermind, 0, 0, 3, 1);
        
        Button btnNederlands = new Button("Nederlands");
        this.add(btnNederlands, 0, 1);
    
        
    
    }

}
