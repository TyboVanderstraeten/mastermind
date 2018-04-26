package cui;

import domein.DomeinController;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
public class UC3Applicatie {

    private final ResourceBundle resourceBundle;
    private final DomeinController domeinController;

    public UC3Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.resourceBundle = resourceBundle;
        this.domeinController = domeinController;

    }

    public void start() {
        Scanner input = new Scanner(System.in);
        boolean geldig = false; // boolean om te checken of eindoverzicht getoond mag worden! enkel tonen bij gewonnen spel! niet bij opslaan!
        while (domeinController.geefSpelbord()[domeinController.geefSpelbord().length - 1][0] == -3) {
            try {
                System.out.print(resourceBundle.getString("opslaanKeuze"));
                String keuze = input.next();
                if ("x".equals(keuze)) {
                    System.out.print(resourceBundle.getString("vraagSpelnaam"));
                    //Door met next() te werken ipv nextLine() kan spelnaam niet leeg zijn en hoeft dit niet extra opgevangen te worden!
                    String spelnaam = input.next();
                    domeinController.registreerSpel(spelnaam);
                    geldig = true;
                    break;
                } else {
                    doePoging();
                    toonSpelbord();
                }
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } //catch (RuntimeException e) {
//                System.out.println(resourceBundle.getString(e.getMessage()));
//                input.nextLine();
//            }
        }
        if (!geldig) {
            geefEindoverzicht();
        }
    }
//String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs",  ///"wit", "zwart"};       ""      o       #     Evaluatie   leeg
//                     0          1           2       3       4       5       6       7           10          9         -2      -1      -3       -4         8

    private void doePoging() {
        Scanner input = new Scanner(System.in);
        int[] poging = new int[domeinController.geefSpelbord()[0].length / 2];
        String[] kleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7"), resourceBundle.getString("8")};
        System.out.printf("%n%s%n%s%n%s", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"), poging.length == 5 ? resourceBundle.getString("kleurIngevenD3") + "\n" : "");

        for (int i = 0; i < poging.length; i++) {
            String kleur = input.next();
            if (!Arrays.asList(kleuren).contains(kleur)) {
                System.out.println(resourceBundle.getString("ongeldigeKleur"));
                i--;
                continue;
            }
            for (int j = 0; j < (poging.length == 5 ? kleuren.length : kleuren.length - 1); j++) {
                if (kleur.equals(resourceBundle.getString(Integer.toString(j)))) {   //nodig omdat ik vreemde bug kreeg als ik 10 ofzo gebruikte. Kreeg waarde van 1 en 0 uit resourcebundle ipv 10. //waarde van lege pin op -5 zetten fixt dit maar maakt code iets moeilijker
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

    private void geefEindoverzicht() {
        String uitvoer = "";

        String[] overzicht = domeinController.geefOverzicht();

        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));         //NOG FOUT ALS ER -5 IN DE CODE ZIT THROWT DIT ERROR
        }

        uitvoer += String.format("%s %s%n", resourceBundle.getString("codeWas"), codeString);
        uitvoer += String.format("%s %d %s%n", resourceBundle.getString("gekraaktInPogingenD1"), Integer.parseInt(overzicht[1]), resourceBundle.getString("gekraaktInPogingenD2"));
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSterren"), overzicht[2]);
        uitvoer += String.format("%s %s%n", resourceBundle.getString("aantalSpellenTotVolgendeSterD1"), overzicht[3]);
        System.out.println(uitvoer);

        
        domeinController.updateSpeler();
        domeinController.berekenScore();
    }
}
