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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.stage.Stage;

//EXCEPTIONS NOG NIET DONE
public class SpelbordScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;

    public SpelbordScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    private void buildGui() {
        int[][] spelbord = dc.geefSpelbord();
        toonSpelbord(spelbord);

        //Positionering
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

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

        Label kleuren = new Label(resourceBundle.getString("allemogelijkekleurenGui"));
        this.add(kleuren, 14, 0, 4, 1);
        this.add(groen, 14, 1);
        this.add(blauw, 14, 2);
        this.add(rood, 15, 1);
        this.add(paars, 15, 2);
        this.add(geel, 16, 1);
        this.add(oranje, 16, 2);
        this.add(grijs, 17, 1);
        this.add(bruin, 17, 2);

        Label lblKleurenIngeven = new Label(resourceBundle.getString("uwPogingGui"));
        this.add(lblKleurenIngeven, 14, 3);

        Button btnVoegToe = new Button(resourceBundle.getString("voegToeGui"));
        this.add(btnVoegToe, 14, 5);

        Label lblFout = new Label();
        this.add(lblFout, 15, 5);

        Button btnOpslaan = new Button(resourceBundle.getString("OpslaanGui"));
        this.add(btnOpslaan, 14, 8);

        btnVoegToe.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int[] poging = new int[spelbord[0].length / 2];
                int aantalpogingen = 0;
                aantalpogingen++;
                String[] alleKleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("8")};
                int teller = 0;
                for (Node node : SpelbordScherm.this.getChildren()) {

                    if (node instanceof TextField) {
                        String kleur = ((TextField) node).getText().toLowerCase().trim();
                        if (!Arrays.asList(alleKleuren).contains(kleur)) {
                            //nog foutlabel                                
                            continue;
                        }
                        for (int j = 0; j < (poging.length == 5 ? alleKleuren.length : alleKleuren.length - 1); j++) {
                            if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {
                                poging[teller] = j;
                                teller++;
                                break;
                            } else {
                                lblFout.setText(resourceBundle.getString("ongeldigeKleur"));
                            }
                        }
                        if (teller == spelbord[0].length / 2) {
                            break;
                        }
                    }
                }
                dc.geefPoging(poging);
                update(dc.geefSpelbord());
                if (Arrays.equals(dc.geefCode(), poging)) {

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Mastermind");
                    alert.setHeaderText("Je hebt gewonnen!");
                    alert.setContentText(geefEindoverzicht());
                    alert.showAndWait();

                    Stage stage = (Stage) (getScene().getWindow());
                    KeuzeScherm keuzeScherm = new KeuzeScherm(dc, resourceBundle);
                    stage.setScene(new Scene(keuzeScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                } else if (aantalpogingen > 12) { //werkt nog niet?
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Mastermind");
                    alert.setHeaderText("Je hebt verloren!");
                    alert.setContentText("Je bent er niet in geslaagd om de code te kraken.");
                    alert.showAndWait();

                    Stage stage = (Stage) (getScene().getWindow());
                    KeuzeScherm keuzeScherm = new KeuzeScherm(dc, resourceBundle);
                    stage.setScene(new Scene(keuzeScherm, 1280, 720));
                    stage.setTitle("Mastermind");
                }
//
            }
        });

        //!!!!
        //HIER NODIG WANT ANDERS MAAKT HIJ TELKENS EEN NIEUW OBJECTJE HIERVAN IN DE EVENT HANDLER VAN BTNOPSLAAN EN ZAL HIJ HET OBJECT DAT ER AL STOND GEWOON OVERSCHRIJVEN MET EEN NIEUW OBJECT IPV HET TE VERWIJDEREN.
        //+ nodes nodig in btnSlaOp om daar te verwijderen
        Label lblSpelnaam = new Label(resourceBundle.getString("SpelnaamGui"));
        TextField txfSpelnaam = new TextField();
        Button btnSlaOp = new Button(resourceBundle.getString("SlaOpGui"));
        //!!!!

        btnOpslaan.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                if (SpelbordScherm.this.getChildren().contains(lblSpelnaam)) {
                    SpelbordScherm.this.getChildren().removeAll(lblSpelnaam, txfSpelnaam, btnSlaOp);
                } else {
                    SpelbordScherm.this.add(lblSpelnaam, 14, 9);
                    SpelbordScherm.this.add(txfSpelnaam, 14, 10, 3, 1);
                    SpelbordScherm.this.add(btnSlaOp, 17, 10);
                }
            }
        }
        );

        btnSlaOp.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                dc.registreerSpel(txfSpelnaam.getText().trim());
                Stage stage = (Stage) (getScene().getWindow());
                stage.setScene(new Scene(new KeuzeScherm(dc, resourceBundle), 1280, 720));
                stage.setTitle("Mastermind");

            }

        }
        );
    }

    private void toonSpelbord(int[][] spelbord) {
        for (int i = 0; i < spelbord.length; i++) {
            for (int j = 0; j < spelbord[i].length; j++) {
                Label label = new Label();
                switch (spelbord[i][j]) {
                    case -2:
                        label.setText("\t");
                        break;
                    case -4:
                        label.setText("\t\t");
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

            if (i < spelbord[0].length / 2) {
                TextField txf = new TextField();
                txf.setPrefWidth(110);
                this.add(txf, 14 + i, 4);

            }
        }
    }

    private void update(int[][] spelbord) {

        for (int i = 0; i < spelbord.length + 1; i++) {
            if (i >= spelbord.length - 1 || Arrays.toString(spelbord[i]).equals(spelbord[i].length / 2 == 5 ? "[-1, -1, -1, -1, -1, -4, -1, -1, -1, -1, -1]" : "[-1, -1, -1, -1, -4, -1, -1, -1, -1]")) {          //OVERLOOPT ELKE RIJ TOT DEZE EEN LEGE RIJ TEGENKOMT EN NEEMT DAN DE VORIGE RIJ OM TE UPDATEN.
                for (Node node : this.getChildren()) {
                    if (node instanceof Label && this.getRowIndex(node) == (i - 1) && this.getColumnIndex(node) < (i == spelbord.length ? spelbord[i - 1].length / 2 : spelbord[i - 1].length) && this.getColumnIndex(node) != spelbord[i - 1].length / 2) { //als pin 8 (leeg) is hoeft deze niet aangepast te worden
                        String kleur = String.format("/images/pin_%d.png", spelbord[i - 1][this.getColumnIndex(node)] == 8 ? -1 : spelbord[i - 1][this.getColumnIndex(node)]);
                        ((Label) node).setGraphic(new ImageView(new Image(getClass().getResourceAsStream(kleur), 45, 45, false, false)));
                    }
                }
                if (spelbord[spelbord.length - 1][0] == -3) {
                    break;
                }
            }

        }

    }

    private String geefEindoverzicht() {
        String uitvoer = "";

        String[] overzicht = dc.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));         //NOG FOUT ALS ER -5 IN DE CODE ZIT THROWT DIT ERROR
        }

        uitvoer += String.format("%s %s%n", resourceBundle.getString("codeWas"), codeString);
        uitvoer += String.format("%s %d %s%n", resourceBundle.getString("gekraaktInPogingenD1"), Integer.parseInt(overzicht[1]), resourceBundle.getString("gekraaktInPogingenD2"));
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSterren"), overzicht[2]);
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSpellenTotVolgendeSterD1"), overzicht[3]);
        return uitvoer;

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
