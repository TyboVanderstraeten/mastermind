package cui;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//EXCEPTIONS DONE
//Nog schrijven van gelijke punten 
public class UC7Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC7Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    public void start() {
        System.out.printf("%s", geefKlassement());
    }

    public String geefKlassement() {
        List<List<String[]>> klassementen = domeinController.geefKlassement();
        String uitvoer = "";
        int moeilijkheidsgraad = 0;
        for (List<String[]> klassement : klassementen) {
            switch (moeilijkheidsgraad) {
                case 0:
                    uitvoer += String.format("%n%n%s%n", resourceBundle.getString("klassementMakkelijk"));
                    break;
                case 1:
                    uitvoer += String.format("%n%n%s%n", resourceBundle.getString("klassementNormaal"));
                    break;
                case 2:
                    uitvoer += String.format("%n%n%s%n", resourceBundle.getString("klassementMoeilijk"));
            }
            for (int teller = 0; teller < klassement.size(); teller++) {
                uitvoer += String.format("%-20s | %s%n", klassement.get(teller)[0], klassement.get(teller)[1]);
            }
            moeilijkheidsgraad++;
        }
        return uitvoer;
    }

}
