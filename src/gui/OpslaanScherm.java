/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author bramd
 */
public class OpslaanScherm {
    private final DomeinController dc;
    private final ResourceBundle resourcebundle;
    public OpslaanScherm(DomeinController dc, ResourceBundle resourcebundle){
        this.dc = dc;
        this.resourcebundle = resourcebundle;
    }
    
    private void buildGui(){
        Label label = new Label();
        TextField txfOpslaan = new TextField();
        
        
    }
}
