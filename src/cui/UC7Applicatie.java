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
        System.out.printf("%s",geefKlassement());
    }

    public String geefKlassement() {
        List<List<String[]>> klassementen = domeinController.geefKlassement();
//        List<String[]> klassementMakkelijk = domeinController.geefKlassementMakkelijk();
//        List<String[]> klassementNormaal = domeinController.geefKlassementNormaal();
//        List<String[]> klassementMoeilijk = domeinController.geefKlassementMoeilijk();
        String uitvoer = "";
        int moeilijkheidsgraad = 0;
        for(List<String[]> klassement : klassementen){
            switch(moeilijkheidsgraad){
                case 0: uitvoer+=String.format("%n%n%s%n", resourceBundle.getString("klassementMakkelijk"));
                break;
                case 1: uitvoer +=String.format("%n%n%s%n", resourceBundle.getString("klassementNormaal"));
                break;
                case 2: uitvoer +=String.format("%n%n%s%n", resourceBundle.getString("klassementMoeilijk"));
            }
            for(int teller = 0; teller < klassement.size(); teller++){
                uitvoer += String.format("%-20s | %s%n", klassement.get(teller)[0], klassement.get(teller)[1]);
            }
            moeilijkheidsgraad++;            
        }
//        String klassement = "";
//        klassement += String.format("%n%s%n%n", resourceBundle.getString("klassementMakkelijk"));
//        for (int teller = 0; teller < klassementMakkelijk.size(); teller++) {
//            klassement += String.format("%s | %s%n", klassementMakkelijk.get(teller)[0], klassementMakkelijk.get(teller)[1]);
//        }
//
//        klassement += String.format("%n%s%n%n", resourceBundle.getString("klassementNormaal"));
//        for (int teller = 0; teller < klassementNormaal.size(); teller++) {
//            klassement += String.format("%s | %s%n", klassementNormaal.get(teller)[0], klassementNormaal.get(teller)[1]);
//        }
//
//        klassement += String.format("%n%s%n%n", resourceBundle.getString("klassementMoeilijk"));
//        for (int teller = 0; teller < klassementMoeilijk.size(); teller++) {
//            klassement += String.format("%s | %s%n", klassementMoeilijk.get(teller)[0], klassementMoeilijk.get(teller)[1]);
//        }
        return uitvoer;
    }

}
