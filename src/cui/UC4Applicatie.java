package cui;

import domein.DomeinController;
import exceptions.SpelnaamBestaatNietException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
public class UC4Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC4Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    public void start() {
        try {
            laadSpel();
            toonSpelbord();
            UC3Applicatie uc3 = new UC3Applicatie(resourceBundle, domeinController);
            uc3.start();
        } catch (NullPointerException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
        }
    }

    public void laadSpel() {
        Scanner input = new Scanner(System.in);
        System.out.println(resourceBundle.getString("maakKeuzeSpel"));
        String[][] spelnamen = domeinController.geefSpellen();
        for (int teller = 0; teller < spelnamen.length; teller++) {
            System.out.printf("%d) %s | %s%n", teller + 1, spelnamen[teller][0], spelnamen[teller][1]);
        }
        String spelnaam = input.next();
        if (Arrays.asList(spelnamen).contains(spelnaam)) {
            domeinController.laadSpel(spelnaam);
            domeinController.verwijderSpel(spelnaam);
        } else {
            throw new SpelnaamBestaatNietException();
        }

    }

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
