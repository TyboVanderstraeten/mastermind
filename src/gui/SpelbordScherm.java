/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author bramd
 */
public class SpelbordScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;

    public SpelbordScherm(DomeinController dc, ResourceBundle resourceBundle) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        buildGui();
    }

    private void buildGui() {        
        dc.kiesMoeilijkheidsgraad(1);
        int[][] spelbord = dc.geefSpelbord();
        for (int i = 0; i < spelbord.length; i++) {
            for (int j = 0; j < spelbord[i].length; j++) {
                Label label = new Label();
                label.setText(resourceBundle.getString(Integer.toString(spelbord[i][j])));
                this.add(label, j, i);
            }
        }
        System.out.println(this.getColumnConstraints().size());     //moet weg, gwn om te testen.
        TextField txfKleur1 = new TextField();
        TextField txfKleur2 = new TextField();
        TextField txfKleur3 = new TextField();
        TextField txfKleur4 = new TextField();
        this.add(txfKleur1, 0, 0);
        this.add(txfKleur2, 1, 0);
        this.add(txfKleur3, 2, 0);
        this.add(txfKleur4, 3, 0);

        Button btnVoegToe = new Button();
        this.add(btnVoegToe, 13, 0);
        Button btnOpslaan = new Button();
        this.add(btnOpslaan, 13, 4);

        btnVoegToe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int[] poging = new int[SpelbordScherm.this.getColumnConstraints().size()];

                String[] alleKleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("x")};
                System.out.printf("%n%s%n%s%n", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"));

                for (Node node : SpelbordScherm.this.getChildren()) {

                    if (node instanceof TextField) {
                        String kleur = ((TextField) node).getText();

                        for (int i = 0; i < poging.length; i++) {
                            if (!Arrays.asList(alleKleuren).contains(kleur)) {
                                //nog foutLabel toevoegen.
                                i--;
                                continue;
                            }
                            for (int j = 0; j < 8; j++) {
                                if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {
                                    poging[i] = j;
                                    break;
                                }
                            }
                        }
                    }
                } 
                dc.geefPoging(poging);

            }

        });

        btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField txfSpelnaam = new TextField();
                txfSpelnaam.setPromptText("uw spelnaam");
                SpelbordScherm.this.add(txfSpelnaam, 0, 16, 2, 1);
                Button btnSlaOp = new Button();
                btnSlaOp.setText("Sla op");
                SpelbordScherm.this.add(btnSlaOp, 4, 16);                
            }
        });
    }

}
