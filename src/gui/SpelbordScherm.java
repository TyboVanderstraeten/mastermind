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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
        System.out.println("test");
        int[][] spelbord = dc.geefSpelbord();
        System.out.println("test");
        this.setHgap(10);
        this.setVgap(10);
        for (int i = 0; i < spelbord.length; i++) {
            for (int j = 0; j < spelbord[i].length; j++) {
                Label label = new Label();
                switch (spelbord[i][j]) {
                    case -2:
                        label.setText("    ");
                        break;
                    case -3:
                        label.setText("#");
                        break;
                    case -4:
                        label.setText("          ");
                        break;
                    default:
                        String kleur = String.format("/images/pin_%d.png", spelbord[i][j]);
                        label.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(kleur), 45, 45, false, false)));//                                
                }
                
                
                //DRAG AND DROP OM MSS LATER TE IMPLEMENTEREN
                
//                label.setOnDragOver(new EventHandler<DragEvent>() {
//                    @Override
//                    public void handle(DragEvent event) {
//                        /* data is dragged over the target */
// /* accept it only if it is not dragged from the same node 
//                        * and if it has a string data */
//                        if (event.getGestureSource() != label
//                                && event.getDragboard().hasString()) {
//                            /* allow for moving */
//                            event.acceptTransferModes(TransferMode.MOVE);
//                        }
//
//                        event.consume();
//                    }
//                });
//                label.setOnDragDropped(new EventHandler<DragEvent>() {
//                    public void handle(DragEvent event) {
//                        /* data dropped */
// /* if there is a string data on dragboard, read it and use it */
//                        Dragboard db = event.getDragboard();
//                        boolean success = false;
//                        if (db.hasString()) {
//                            label.setText(db.getString());
//                            success = true;
//                        }
//                        /* let the source know whether the string was successfully 
//         * transferred and used */
//                        event.setDropCompleted(success);
//
//                        event.consume();
//                    }
//                });
                this.add(label, j, i);
            }
        }
        System.out.println("test");
        System.out.println(this.getColumnConstraints().size());     //moet weg, gwn om te testen.

        Label groen = new Label(resourceBundle.getString("0"));
        Label blauw = new Label(resourceBundle.getString("1"));
        Label rood = new Label(resourceBundle.getString("2"));
        Label paars = new Label(resourceBundle.getString("3"));
        Label geel = new Label(resourceBundle.getString("4"));
        Label oranje = new Label(resourceBundle.getString("5"));
        Label grijs = new Label(resourceBundle.getString("6"));
        Label bruin = new Label(resourceBundle.getString("7"));

        groen.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_0.png"), 45, 45, false, false)));
        //dragDropEvent(groen);
        blauw.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_1.png"), 45, 45, false, false)));
        //dragDropEvent(blauw);
        rood.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_2.png"), 45, 45, false, false)));
        //dragDropEvent(rood);
        paars.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_3.png"), 45, 45, false, false)));
        //dragDropEvent(paars);
        geel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_4.png"), 45, 45, false, false)));
        //dragDropEvent(geel);
        oranje.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_5.png"), 45, 45, false, false)));
        //dragDropEvent(oranje);
        grijs.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_6.png"), 45, 45, false, false)));
        //dragDropEvent(grijs);
        bruin.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pin_7.png"), 45, 45, false, false)));
        //dragDropEvent(bruin);

        Label kleuren = new Label("ALLE MOGELIJKE KLEUREN");
        this.add(kleuren, 14, 0, 4, 1);
        this.add(groen, 14, 1);
        this.add(blauw, 14, 2);
        this.add(rood, 15, 1);
        this.add(paars, 15, 2);
        this.add(geel, 16, 1);
        this.add(oranje, 16, 2);
        this.add(grijs, 17, 1);
        this.add(bruin, 17, 2);

        
        TextField txfKleur1 = new TextField();
        txfKleur1.setPrefWidth(30);
        TextField txfKleur2 = new TextField();
        txfKleur2.setPrefWidth(30);
        TextField txfKleur3 = new TextField();
        txfKleur3.setPrefWidth(30);
        TextField txfKleur4 = new TextField();
        txfKleur4.setPrefWidth(30);
        this.add(txfKleur1, 14, 6);
        this.add(txfKleur2, 15, 6);
        this.add(txfKleur3, 16, 6);
        this.add(txfKleur4, 17, 6);
        
        
        Button btnVoegToe = new Button("Voeg Toe");
        this.add(btnVoegToe, 14, 12);
        Button btnOpslaan = new Button("Opslaan");
        this.add(btnOpslaan, 17, 12);

        btnVoegToe.setOnAction(new EventHandler<ActionEvent>() {
            //WERKT NOG NIET
            
            @Override
            public void handle(ActionEvent event) {
//
//                int[] poging = new int[SpelbordScherm.this.getColumnConstraints().size()];
//
//                String[] alleKleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("x")};
//                
//
//                for (Node node : SpelbordScherm.this.getChildren()) {
//
//                    if (node instanceof TextField) {
//                        String kleur = ((TextField) node).getText();
//
//                        for (int i = 0; i < poging.length; i++) {
//                            if (!Arrays.asList(alleKleuren).contains(kleur)) {
//                                //nog foutLabel toevoegen.
//                                i--;
//                                continue;
//                            }
//                            for (int j = 0; j < 8; j++) {
//                                if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {
//                                    poging[i] = j;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                } 
//                dc.geefPoging(poging);
//
            }

        });

        btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                TextField txfSpelnaam = new TextField();
//                txfSpelnaam.setPromptText("uw spelnaam");
//                SpelbordScherm.this.add(txfSpelnaam, 0, 16, 2, 1);
//                Button btnSlaOp = new Button();
//                btnSlaOp.setText("Sla op");
//                SpelbordScherm.this.add(btnSlaOp, 4, 16);
            }
        });

    }

    
    //OOK VOOR DRAG AND DROP
    
    
//    private void dragDropEvent(Label label) {
//        label.setOnDragDetected(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                /* drag was detected, start a drag-and-drop gesture*/
// /* allow any transfer mode */
//                Dragboard db = label.startDragAndDrop(TransferMode.ANY);
//
//                /* Put a string on a dragboard */
//                ClipboardContent content = new ClipboardContent();
//                content.putString(label.getText());
//                db.setContent(content);
//                event.consume();
//            }
//        });
//    }

}
