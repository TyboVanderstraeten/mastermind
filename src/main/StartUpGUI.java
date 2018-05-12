package main;

import domein.DomeinController;
import gui.TaalScherm;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * StartUp van de guiApplicatie.
 * 
 */
public class StartUpGUI extends Application {

    /**
     * main methode die een nieuw object van DomeinController aanmaakt en
     * dit meegeeft aan een nieuw object van TaalScherm.
     * Dit TaalScherm object wordt dan meegegeven aan de Scene die dan op het scherm zal vershijnen na show().
     * De guiApplicatie wordt hier gestart.
     * 
     * @param primaryStage de stage die op het scherm zal verschijnen.
     */
   @Override
    public void start(Stage primaryStage) {
        DomeinController dc = new DomeinController();
        TaalScherm ts = new TaalScherm(dc);
        Scene scene = new Scene(ts, 1280, 720);
        Image appIcon = new Image("/images/pictogram.png");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mastermind");
        primaryStage.getIcons().add(appIcon);
        primaryStage.show();
    }

    /**
     * Main methode.
     * 
     * @param args default parameter van main
     */
    public static void main(String[] args) {
        launch(args);
    }

}
