package cui;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UC7Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC7Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    public void start() {
        
    }

    public String geefKlassement() {
        List<String[]> klassementMakkelijk = domeinController.geefKlassementMakkelijk();
        List<String[]> klassementNormaal = domeinController.geefKlassementNormaal();
        List<String[]> klassementMoeilijk = domeinController.geefKlassementMoeilijk();
        String klassement = "";
        klassement += String.format("%s", "Klassement voor makkelijke moeilijkheidsgraad");
        for (int teller = 0; teller < klassementMakkelijk.size(); teller++) {
            System.out.printf("%s | %s%n",klassementMakkelijk.get(teller)[0],klassementMakkelijk.get(teller)[1]);    
        }
        
        klassement += String.format("%s", "Klassement voor normale moeilijkheidsgraad");
        for (int teller = 0; teller < klassementNormaal.size(); teller++) {
            System.out.printf("%s | %s%n",klassementNormaal.get(teller)[0],klassementNormaal.get(teller)[1]);    
        }
        
        klassement += String.format("%s", "Klassement voor moeilijke moeilijkheidsgraad");
        for (int teller = 0; teller < klassementMoeilijk.size(); teller++) {
            System.out.printf("%s | %s%n",klassementMoeilijk.get(teller)[0],klassementMoeilijk.get(teller)[1]);    
        }
    }

}
