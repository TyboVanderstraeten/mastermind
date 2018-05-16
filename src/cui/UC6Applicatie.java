package cui;

import domein.DomeinController;
import exceptions.TegenspelerNaamBestaatNietException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
/**
 * Bevat het volledige verloop van UC6.
 *
 */
public class UC6Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    /**
     * Class constructor. Zorgt ervoor dat de domeinController en resourceBundle
     * geïnitialiseert worden.
     *
     * @param resourceBundle het Object van de ResourceBundle.
     * @param domeinController het Object van de DomeinController.
     */
    public UC6Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    /**
     * Bevat het volledige verloop van UC6.
     */
    public void start() {
        try {
            if (domeinController.geefOpenUitdagingen() != null) {
                System.out.println(resourceBundle.getString("onafgewerkteUitdagingen"));                
            } else {
                aanvaardUitdaging();
            }
        } catch (NullPointerException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
        }
    }

    /**
     * Bevat het volledige verloop om een uitdaging te aanvaarden.
     */
    private void aanvaardUitdaging() {
        Scanner input = new Scanner(System.in);

        String[][] uitdagingen = domeinController.geefUitdaging();
        String[] spelernamen = new String[uitdagingen.length];
        if (uitdagingen.length != 0) {
            System.out.println(resourceBundle.getString("kiesUitdaging"));
            for (int teller = 0; teller < uitdagingen.length; teller++) {
                System.out.printf("%d) %s%n", teller + 1, Arrays.toString(uitdagingen[teller]).replace("[", "").replace("]", "").replace(",", " | "));
                spelernamen[teller] = uitdagingen[teller][0];
            }
            String spelnaam = input.nextLine();
            if (!Arrays.asList(spelernamen).contains(spelnaam)) {
                throw new TegenspelerNaamBestaatNietException();
            } else {
                domeinController.laadUitdaging(spelnaam);
                toonSpelbord();
                UC3Applicatie uc3 = new UC3Applicatie(resourceBundle, domeinController);
                uc3.start();
            }
        } else {
            System.out.println(resourceBundle.getString("geenUitdagingen"));
        }

    }

    /**
     * Bevat het volledige verloop om een spelbord op het scherm weer te geven.
     */
    private void toonSpelbord() {
        System.out.println("\n\n");
        int[][] spelbord = domeinController.geefSpelbord();
        //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "wit", "zwart"};
        for (int[] rij : spelbord) {
            String x = Arrays.toString(rij).replace(",", " ").replace("[", "| ").replace("]", " |").replace("-3", String.format("%-7s", "#")).replace("-2", String.format("%-7s", " ")).replace("-1", String.format("%-7s", "o")).replace("-4", rij == spelbord[spelbord.length - 1] ? String.format("\t\t%10s", " ") : "\t\t" + resourceBundle.getString("evaluatie"));
            for (int pin : rij) {
                if (pin >= 0) {
                    x = x.replace(Integer.toString(pin), String.format("%-7s", resourceBundle.getString(Integer.toString(pin))));
                }
            }
            System.out.println(x);
        }
        System.out.println("\n\n");
    }
}
