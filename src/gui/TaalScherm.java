package gui;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class TaalScherm extends GridPane {

    public TaalScherm() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
        //     ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
        //     ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);

        //IMAGES
        Image vlagNederland = new Image("/images/vlag_nederland.png");
        ImageView ivNederland = new ImageView(vlagNederland);
        ivNederland.setFitHeight(100);
        ivNederland.setFitWidth(150);

        Image vlagFrankrijk = new Image("/images/vlag_frankrijk.png");
        ImageView ivFrankrijk = new ImageView(vlagFrankrijk);
        ivFrankrijk.setFitHeight(100);
        ivFrankrijk.setFitWidth(150);

        Image vlagEngeland = new Image("/images/vlag_engeland.png");
        ImageView ivEngeland = new ImageView(vlagEngeland);
        ivEngeland.setFitHeight(100);
        ivEngeland.setFitWidth(150);

        //Objects
        Button btnNederlands = new Button();
        btnNederlands.setGraphic(ivNederland);
        this.add(btnNederlands, 0, 0);

        Button btnFrans = new Button();
        btnFrans.setGraphic(ivFrankrijk);
        this.add(btnFrans, 1, 0);

        Button btnEngels = new Button();
        btnEngels.setGraphic(ivEngeland);
        this.add(btnEngels, 2, 0);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

    }

}
