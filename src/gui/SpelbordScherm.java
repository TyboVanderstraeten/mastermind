package gui;

import domein.DomeinController;
import exceptions.FoutKleurException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//EXCEPTIONS DONE
/**
 * Het SpelbordScherm + het verloop hiervoor.
 *
 */
public class SpelbordScherm extends GridPane {

    private final DomeinController dc;
    private final ResourceBundle resourceBundle;
    private final KeuzeScherm keuzeScherm;
    private int aantalPogingen = 0;

    /**
     * Class constructor. Initialiseert dc, resourceBundle en keuzeScherm.
     *
     * @param dc instantie van DomeinController.
     * @param resourceBundle instantie van ResourceBundle.
     * @param keuzeScherm instantie van KeuzeScherm.
     */
    public SpelbordScherm(DomeinController dc, ResourceBundle resourceBundle, KeuzeScherm keuzeScherm) {
        this.dc = dc;
        this.resourceBundle = resourceBundle;
        this.keuzeScherm = keuzeScherm;
        buildGui();
    }

    /**
     * Bevat het volledige verloop van "SpelbordScherm"
     */
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
                try {
                    lblFout.setText(null);
                    int[] poging = new int[spelbord[0].length / 2];
                    //OOK VOOR TEXTFIELD (CONTROLE)
                    //String[] alleKleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("8")};
                    int teller = 0;
                    for (Node node : SpelbordScherm.this.getChildren()) {

                        if (node instanceof ComboBox) {
                            //VOOR TEXTFIELDS
//                            String kleur = ((ComboBox) node).getText().toLowerCase().trim();
//                            if (!Arrays.asList(alleKleuren).contains(kleur)) {
//                                throw new FoutKleurException();
//                            }
//                            for (int j = 0; j < (poging.length == 5 ? alleKleuren.length : alleKleuren.length - 1); j++) {
//                                if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {
//                                    poging[teller] = j;
//                                    teller++;
//                                    break;
//                                }
//                            }
//                            if (teller == spelbord[0].length / 2) {
//                                break;
//                            }

                            //VOOR COMBOBOX
                            int kleur = ((ComboBox) node).getSelectionModel().getSelectedIndex();
                            if(kleur == -1){
                                throw new FoutKleurException();
                            }
                            poging[teller] = kleur;
                            teller++;
                        }
                    }                    
                    aantalPogingen++;
                    dc.geefPoging(poging);
                    update(dc.geefSpelbord());
                    if (Arrays.equals(dc.geefCode(), poging)) {
                        dc.berekenScore(true);

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Mastermind");
                        alert.setHeaderText(resourceBundle.getString("gewonnen"));
                        alert.setContentText(geefEindoverzicht());
                        alert.showAndWait();

                        Stage stage = (Stage) (getScene().getWindow());                        
                        stage.setScene(keuzeScherm.getScene());
                        stage.setTitle("Mastermind");
                    } else if (aantalPogingen > 11) {
                        dc.berekenScore(false);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Mastermind");
                        alert.setHeaderText(resourceBundle.getString("verloren"));
                        alert.setContentText(geefEindoverzichtVerloren());
                        alert.showAndWait();

                        Stage stage = (Stage) (getScene().getWindow());                        
                        stage.setScene(keuzeScherm.getScene());
                        stage.setTitle("Mastermind");
                    }
//
                } catch (InputMismatchException | IllegalArgumentException e) {
                    lblFout.setText(resourceBundle.getString(e.getMessage()));
                }

            }
        });

        //!!!!
        //HIER NODIG WANT ANDERS MAAKT HIJ TELKENS EEN NIEUW OBJECTJE HIERVAN IN DE EVENT HANDLER VAN BTNOPSLAAN EN ZAL HIJ HET OBJECT DAT ER AL STOND GEWOON OVERSCHRIJVEN MET EEN NIEUW OBJECT IPV HET TE VERWIJDEREN.
        //+ nodes nodig in btnSlaOp om daar te verwijderen
        Label lblSpelnaam = new Label(resourceBundle.getString("SpelnaamGui"));
        TextField txfSpelnaam = new TextField();
        Button btnSlaOp = new Button(resourceBundle.getString("SlaOpGui"));
        //!!!!

        btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
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

        btnSlaOp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                try {
                    System.out.println(txfSpelnaam.getText().trim());
                    dc.registreerSpel(txfSpelnaam.getText().trim());
                    Stage stage = (Stage) (getScene().getWindow());
                    stage.setScene(keuzeScherm.getScene());                    
                } catch (RuntimeException e) {
                    lblFout.setText(resourceBundle.getString(e.getMessage()));
                }
            }

        }
        );
    }

    /**
     * Deze methode overloopt het spelbord en zet de cijfers om om naar pinnen op het scherm (images)
     * 
     * @param spelbord een int[][] die het spelbord representeert.
     */
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
//                TextField txf = new TextField();
//                txf.setPrefWidth(110);
//                this.add(txf, 14 + i, 4);
                  ComboBox cB = new ComboBox();
                  cB.setPromptText(resourceBundle.getString("kleur")+ (i+1));
                  cB.setPrefWidth(110);
                  cB.getItems().addAll(resourceBundle.getString("0"),resourceBundle.getString("1"),resourceBundle.getString("2"),resourceBundle.getString("3"),resourceBundle.getString("4"),resourceBundle.getString("5"),resourceBundle.getString("6"),resourceBundle.getString("7"));
                  if(spelbord[0].length/2==5){
                      cB.getItems().add(resourceBundle.getString("8"));
                  }
                  
                  this.add(cB, 14+i, 4);
            }
        }
    }

    /**
     * Deze methode zal de pinnen op het scherm updaten.
     * Hij zal de juiste rij nemen en deze updaten ipv het hele scherm te resetten.
     * 
     * @param spelbord int[][] die het spelbord moet representeren.
     */
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

    /**
     * Deze methode geeft een String terug die het eind overzicht bevat indien de speler gewonnen is.
     * 
     * @return String met het eindoverzicht.
     */
    private String geefEindoverzicht() {
        String uitvoer = "";

        String[] overzicht = dc.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("%s %s%n", resourceBundle.getString("codeWas"), codeString);
        uitvoer += String.format("%s %d %s%n", resourceBundle.getString("gekraaktInPogingenD1"), Integer.parseInt(overzicht[1]), resourceBundle.getString("gekraaktInPogingenD2"));
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSterren"), overzicht[2]);
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSpellenTotVolgendeSterD1"), overzicht[3]);
        return uitvoer;

    }

    /**
     * Deze methode geeft het eind overzicht terug indien de speler verloren is.
     * 
     * @return String met het eind overzicht.
     */
    private String geefEindoverzichtVerloren() {
        String uitvoer = "";

        String[] overzicht = dc.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("%s %s%n", resourceBundle.getString("codeWas"), codeString);
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
