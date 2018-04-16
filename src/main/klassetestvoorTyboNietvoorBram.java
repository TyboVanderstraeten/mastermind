package main;

import gui.KeuzeScherm;
import gui.LoginScherm;
import gui.RegistreerScherm;
import gui.TaalScherm;
import gui.WelkomScherm;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class klassetestvoorTyboNietvoorBram extends Application {

    @Override
    public void start(Stage primaryStage) {

        TaalScherm ts = new TaalScherm();
        Scene scene = new Scene(ts, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mastermind");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
