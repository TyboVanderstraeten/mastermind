
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;

public class UC4Applicatie {
    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;
    
    
    public UC4Applicatie(ResourceBundle resourceBundle, DomeinController domeinController){
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }
    
    public void start(){
    
    }

    
}
