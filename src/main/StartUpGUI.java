package main;

import domein.DomeinController;
import gui.KeuzeScherm;
import gui.LoginScherm;
import gui.RegistreerScherm;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        DomeinController dc = new DomeinController();
        WelkomScherm welkomScherm = new WelkomScherm(dc);

        Scene scene = new Scene(welkomScherm, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mastermind");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
