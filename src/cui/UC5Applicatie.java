
package cui;

import domein.DomeinController;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UC5Applicatie {
    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;
    
    
    public UC5Applicatie(ResourceBundle resourceBundle, DomeinController domeinController){
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }
    
    public void start(){
       
    }
    
    public void kiesTegenspeler(){
        Scanner input = new Scanner(System.in);
        System.out.println(resourceBundle.getString("maakKeuzeTegenspeler"));
        List<String> tegenspelerNamen = domeinController.geefTegenSpelers();
        for(int teller = 0; teller < tegenspelerNamen.size(); teller++){
            System.out.printf("%d) %s%n",teller+1,tegenspelerNamen[teller]);
        }
    }
   
    
}
