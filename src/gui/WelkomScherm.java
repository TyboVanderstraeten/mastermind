package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WelkomScherm extends GridPane {

    public WelkomScherm() {
        Label lblWelkom = new Label("Mastermind");
        this.add(lblWelkom, 0, 0, 3, 1);

        Button btnMeldAan = new Button("Meld aan");
        this.add(btnMeldAan, 0, 1);

        Button btnRegistreer = new Button("Registreer");
        this.add(btnRegistreer, 1, 1);

        Button btnStop = new Button("Stop");
        this.add(btnStop, 2, 1);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        //EventHandling
        //Meldaan knop die naar het loginscherm leidt
        btnMeldAan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                LoginScherm loginScherm = new LoginScherm();
                stage.setScene(new Scene(loginScherm,300,200));
                stage.setTitle("Aanmelden");
                stage.show();
            }
        });

        //Registreer knop die naar het registreerscherm leidt
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        //Stop knop die het programma stopt
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

    }

}
