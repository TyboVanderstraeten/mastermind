package cui;

import domein.DomeinController;
import exceptions.FoutKleurException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
/**
 * Bevat het volledige verloop van UC3.
 * 
 */
public class UC3Applicatie {

    private final ResourceBundle resourceBundle;
    private final DomeinController domeinController;
    private int aantalPogingen = 0;

    /**
     * Class constructor.
     * Zorgt ervoor dat de domeinController en resourceBundle geïnitialiseert worden.
     * 
     * @param resourceBundle het Object van de ResourceBundle.
     * @param domeinController het Object van de DomeinController.
     */
    public UC3Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.resourceBundle = resourceBundle;
        this.domeinController = domeinController;

    }

    /**
     * Bevat het volledige verloop van UC3.
     */
    public void start() {
        Scanner input = new Scanner(System.in);
        while (domeinController.geefSpelbord()[domeinController.geefSpelbord().length - 1][0] == -3) {
            try {
                System.out.print(resourceBundle.getString("opslaanKeuze"));
                String keuze = input.next();
                if ("x".equals(keuze)) {
                    System.out.print(resourceBundle.getString("vraagSpelnaam"));
                    //Door met next() te werken ipv nextLine() kan spelnaam niet leeg zijn en hoeft dit niet extra opgevangen te worden!
                    String spelnaam = input.next();
                    domeinController.registreerSpel(spelnaam);
                    break;
                } else {
                    doePoging();
                }
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (RuntimeException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            }
        }
    }
//String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs",  ///"wit", "zwart"};       ""      o       #     Evaluatie   leeg
//                     0          1           2       3       4       5       6       7           10          9         -2      -1      -3       -4         8

    /**
     * Bevat het volledige verloop om een poging te doen en door te geven aan de domeinController.
     */
    private void doePoging() {
        try {
            Scanner input = new Scanner(System.in);
            int[] poging = new int[domeinController.geefSpelbord()[0].length / 2];
            String[] kleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("8")};
            System.out.printf("%n%s%n%s%n%s", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"), poging.length == 5 ? resourceBundle.getString("kleurIngevenD3") + "\n" : "");

            for (int i = 0; i < poging.length; i++) {
                String kleur = input.next();
                if (!Arrays.asList(kleuren).contains(kleur)) {
                    throw new FoutKleurException();
                }
                for (int j = 0; j < (poging.length == 5 ? kleuren.length : kleuren.length - 1); j++) {
                    if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {
                        poging[i] = j;
                        break;
                    }
                }
            }
            aantalPogingen++;
            domeinController.geefPoging(poging);
            toonSpelbord();
            if (Arrays.equals(domeinController.geefCode(), poging)) {
                geefEindoverzicht();
                domeinController.berekenScore();
            } else if (aantalPogingen > 11) {
                geefEindoverzichtVerloren();
            }
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
        }

    }

    /**
     * Bevat het volledige verloop om het spelbord op het scherm weer te geven.
     */
    private void toonSpelbord() {
        System.out.println("\n\n");
        int[][] spelbord = domeinController.geefSpelbord();
        //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "wit", "zwart"};
        for (int[] rij : spelbord) {
            String x = Arrays.toString(rij).replace(",", " ").replace("[", "| ").replace("]", " |").replace("-3", String.format("%-7s", "#")).replace("-2", String.format("%-7s", " ")).replace("-1", String.format("%-7s", "o")).replace("-4", rij == spelbord[spelbord.length - 1] ? String.format("\t\t%10s", " ") : "\t\t" + resourceBundle.getString("evaluatie")).replace("8", String.format("%-7s", "o"));

            for (int i = rij.length - 1; i >= 0; i--) {         //ik begin vanachter omdat groen als nummer 1 heeft en wit als nummer 10. 
                if (rij[i] >= 0) {                              //Als ik dit niet zou doen zou bij de replace van groen (1) ook de 1 van 10 nemen en dan print hij dus "groen" bij de evaluatiepinnen. replaceFirst is met regex en ik heb de waarde van het nummer nodig in de resbundle.
                    //Andere oplossing zou zijn om wit en zwart in resourcebundle in 1 en 2 te veranderen maar dit werkt ook.
                    x = x.replace((Integer.toString(rij[i])), String.format("%-7s", resourceBundle.getString(Integer.toString(rij[i]))));
                }
            }
            System.out.println(x);
        }
        System.out.println("\n\n");
    }

    /**
     * Bevat het volledige verloop om het eind overzicht op het scherm te brengen.
     */
    private void geefEindoverzicht() {
        String uitvoer = "";

        String[] overzicht = domeinController.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("%s %s%n", resourceBundle.getString("codeWas"), codeString);
        uitvoer += String.format("%s %d %s%n", resourceBundle.getString("gekraaktInPogingenD1"), Integer.parseInt(overzicht[1]), resourceBundle.getString("gekraaktInPogingenD2"));
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSterren"), overzicht[2]);
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSpellenTotVolgendeSterD1"), overzicht[3]);
        System.out.println(uitvoer);

    }

    /**
     * Bevat het volledige verloop om het eindoverzicht bij een verloren spel op het scherm te brengen.
     */
    private void geefEindoverzichtVerloren() {
        String uitvoer = "";

        String[] overzicht = domeinController.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("%n%s%n%s %s%n", resourceBundle.getString("verloren"), resourceBundle.getString("codeWas"), codeString);
        System.out.println(uitvoer);

    }
}
