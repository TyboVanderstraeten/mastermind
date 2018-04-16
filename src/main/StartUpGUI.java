package main;

import domein.DomeinController;
import gui.TaalScherm;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGUI extends Application {

   @Override
    public void start(Stage primaryStage) {
        DomeinController dc = new DomeinController();
        TaalScherm ts = new TaalScherm(dc);
        Scene scene = new Scene(ts, 1280, 720);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mastermind");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
