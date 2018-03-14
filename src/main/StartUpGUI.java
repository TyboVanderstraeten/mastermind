package main;

import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class StartUpGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        WelkomScherm welkomScherm = new WelkomScherm();
        Scene scene = new Scene(welkomScherm,300,200);
        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Mastermind");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
