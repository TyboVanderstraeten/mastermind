package gui;

import domein.DomeinController;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

//EXCEPTIONS DONE
public class MoeilijkheidsgraadKeuzeScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    public MoeilijkheidsgraadKeuzeScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
    }

    private void buildGui() {

        Button btnMakkelijk = new Button(resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
        this.add(btnMakkelijk, 0, 0);

        Button btnNormaal = new Button(resourceBundle.getString("normaleMoeilijkheidsgraad"));
        this.add(btnNormaal, 1, 0);

        Button btnMoeilijk = new Button(resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
        this.add(btnMoeilijk, 2, 0);
    }

}
