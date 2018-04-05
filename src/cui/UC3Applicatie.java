
package cui;

import domein.DomeinController;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;


public class UC3Applicatie {

    private final ResourceBundle resourceBundle;
    private final DomeinController domeinController;

    public UC3Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.resourceBundle = resourceBundle;
        this.domeinController = domeinController;
        
    }

    public void start() {
        while (domeinController.geefSpelbord()[domeinController.geefSpelbord().length - 1][0] == -3) {
            doePoging();
            toonSpelbord();
        }
    }
//String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs",  ///"wit", "zwart"};       ""      o       #
//                     0          1           2       3       4       5       6       7           8          9         -2      -1      -3
    private void doePoging() {
        Scanner input = new Scanner(System.in);
        int[] poging = new int[domeinController.geefSpelbord()[0].length / 2];
        String[] kleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7")};
        System.out.printf("%n%s%n%s%n", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"));
        for (int i = 0; i < poging.length; i++) {
            String kleur = input.next();
            if (!Arrays.asList(kleuren).contains(kleur)) {
                System.out.println(resourceBundle.getString("ongeldigeKleur"));
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
        domeinController.geefPoging(poging);
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
    
    
    private void geefEindoverzicht() {
        String uitvoer = "";
        String[] overzicht = domeinController.geefOverzicht();
        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("%s: %s%n", resourceBundle.getString("codeWas"), codeString);
        uitvoer += String.format("%s %d %s%n", resourceBundle.getString("gekraaktInPogingenD1"), Integer.parseInt(overzicht[1]), resourceBundle.getString("gekraaktInPogingenD2"));
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSterren"), overzicht[2]);
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSpellenTotVolgendeSterD1"), overzicht[3]);
        System.out.println(uitvoer);
    }
}