/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;

/**
 *
 * @author bramd
 */
public class UC4Applicatie {
    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;
    
    
    public UC4Applicatie(DomeinController domeinController, ResourceBundle resourceBundle){
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }
    
    public void start(){
        laadSpel();
    }
    
    private void laadSpel(){
        System.out.println("Kies een spel: ");
        String[] spellen = domeinController.geefSpellen();
        for(String spel : spellen){
            
        }
    }
}
