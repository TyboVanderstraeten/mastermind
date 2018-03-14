package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class WelkomScherm extends GridPane {

    public WelkomScherm() {
        Label lblWelkom = new Label("Mastermind");
        this.add(lblWelkom, 0, 0, 2, 1);

        Button btnMeldAan = new Button("Meld aan");
        this.add(btnMeldAan, 0, 1);

        Button btnRegistreer = new Button("Registreer");
        this.add(btnRegistreer, 1, 1);

        //EventHandling
        //Meldaan knop die naar het loginscherm leidt
        //WERKT NIET! Manier vinden om als button klik -> loginscherm openen!
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginScherm loginScherm = new LoginScherm();
                Scene scene = new Scene(loginScherm, 300, 200);
            }
        });

        //Registreer knop die naar het registreerscherm leidt
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }

}
