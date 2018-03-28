package cui;

import domein.DomeinController;
import exceptions.MeldAanRegistreerKeuzeException;
import exceptions.TaalKeuzeException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * De applicatie zelf,de verschillende handelingen worden in de juiste volgorde
 * uitgevoerd.
 *
 */
public class MastermindApplicatie {

    private final DomeinController domeinController;
    private ResourceBundle resourceBundle;

    /**
     * Class constructor. geeft het attribuut domeinController de waarde van de
     * parameter.
     *
     * @param domeinController object van DomeinController, deze klasse bevat
     * alle methodes die in deze klasse moeten worden aangeroepen.
     */
    public MastermindApplicatie(DomeinController domeinController) {
        this.domeinController = domeinController;
    }

    /**
     * Bevat alle handelingen.
     * <ul>
     * <li> aanmelden/registreren
     * <li> een overzicht van de mogelijke moeilijkheidsgraden en het
     * respectievelijke aantal wins
     * <li> teruggeven van het spelbord
     * </ul>
     */
    public void startApplicatie() {

        //moet aangepast worden, gwn gebruikt om te testen.
        //NIET IN VOLGORDE ETC.        
        // Try catch binnen de try catch vermijden! Anders oplossen :) (Voor opnieuw vragen spelersnaam (apart), wachtwoord (apart),...
        geefTaal();

        meldAanRegistreer();

        kiesMoeilijkheidsgraad();

        geefOverzicht();
        while (domeinController.geefSpelbord()[domeinController.geefSpelbord().length - 1][0] == -3) {          //ZOLANG CODELIJN GEMASKEERD IS (# BEVAT)
            toonSpelbord();
            doePoging();

        }

        toonSpelbord();

        geefEindoverzicht();

    }

    private void meldAanRegistreer() {
        boolean geldig = false;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println(resourceBundle.getString("aanmeldenRegistratie"));
                System.out.println("1: " + resourceBundle.getString("meldAan").toUpperCase());
                System.out.println("2: " + resourceBundle.getString("registreer").toUpperCase());
                switch (input.nextInt()) {
                    case 1:
                        System.out.print(resourceBundle.getString("naam"));
                        String spelersnaam = input.next();
                        System.out.print(resourceBundle.getString("wachtwoord"));
                        domeinController.meldAan(spelersnaam, input.next());
                        break;
                    case 2:

                        System.out.print(resourceBundle.getString("naam2"));
                        String spelernaam = input.next();
                        System.out.print(resourceBundle.getString("wachtwoord"));
                        String wachtwoord = input.next();
                        System.out.print(resourceBundle.getString("wachtwoordHerhaling"));
                        System.out.println("");
                        domeinController.registreer(spelernaam, wachtwoord, input.next());
                        break;
                    default:
                        throw new MeldAanRegistreerKeuzeException(); //werkt niet, geeft rode lijntjes?!
                }
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!geldig);
        System.out.println(domeinController.geefSpelersnaam());
    }

    private void geefTaal() {
        Scanner input = new Scanner(System.in);
        boolean geldig = false;
        do {
            try {
                System.out.println("Choose your language: (enter the number) \n1: Nederlands \n2: Français \n3: English");

                switch (input.nextInt()) {
                    case 1:
                        resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
                        geldig = true;
                        break;
                    case 2:
                        resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
                        geldig = true;
                        break;
                    case 3:
                        resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);
                        geldig = true;
                        break;
                    default:
                        throw new TaalKeuzeException(); //werkt niet, geeft rode lijntjes?!
                }
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!geldig);
    }

    private void kiesMoeilijkheidsgraad() {
        Scanner input = new Scanner(System.in);
        boolean geldig = false;
        do {
            try {
                System.out.println(resourceBundle.getString("keuzeMoeilijkheid"));
                System.out.println("1: " + resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
                System.out.println("2: " + resourceBundle.getString("normaleMoeilijkheidsgraad"));
                System.out.println("3: " + resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
                domeinController.kiesMoeilijkheidsgraad(input.nextInt());
                geldig = true;
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!geldig);
    }

    private void toonSpelbord() {
        System.out.println("\n\n");
        int[][] spelbord = domeinController.geefSpelbord();
        //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "wit", "zwart"};
        for (int[] rij : spelbord) {
            String x = Arrays.toString(rij).replace(",", " ").replace("[", "| ").replace("]", " |").replace("-3", String.format("%-7s", "#")).replace("-2", String.format("%-7s", " ")).replace("-1", String.format("%-7s", "o")).replace("-4", rij==spelbord[spelbord.length-1]?String.format("\t\t%10s", " "):"\t\tEvaluatie:");
            for (int pin : rij) {
                if (pin >= 0) {
                    x = x.replace(Integer.toString(pin), String.format("%-7s", resourceBundle.getString(Integer.toString(pin))));
                }                
            }
            System.out.println(x);
        }
        System.out.println("\n\n");
    }

    private void geefOverzicht() {
        String[][] overzicht = domeinController.startMasterMind();

        for (String[] a : overzicht) {
            for (String x : a) {

            }
        }

        for (String[] a : overzicht) {
            System.out.println(Arrays.toString(a));
        }

    }

    //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs",  ///"wit", "zwart"};       ""      o       #
    //                      0          1           2       3       4       5       6       7           8          9         -2      -1      -3
    private void doePoging() {
        Scanner input = new Scanner(System.in);
        int[] poging = new int[domeinController.geefSpelbord()[0].length / 2];
        String[] kleuren = {resourceBundle.getString("0"), resourceBundle.getString("1"), resourceBundle.getString("2"), resourceBundle.getString("3"), resourceBundle.getString("4"), resourceBundle.getString("5"), resourceBundle.getString("6"), resourceBundle.getString("7")};
        System.out.printf("%n%s%n%s%n", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"));
        for (int i = 0; i < poging.length; i++) {
            String kleur = input.next();
            if (!Arrays.asList(kleuren).contains(kleur)) {
                System.out.println("Ongeldige kleur");
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

    private void geefEindoverzicht() {
        String uitvoer = "";
        String[] overzicht = domeinController.geefOverzicht();
        String[] code = overzicht[0].replace(",", " ").replace("[", "").replace("]", "").replaceAll("\\s+", "").split("");

        String codeString = "";
        for (int i = 0; i < code.length; i++) {
            codeString += String.format("%-7s", resourceBundle.getString(code[i]));
        }

        uitvoer += String.format("De code was: %s%n", codeString);
        uitvoer += String.format("Gekraakt in %d poging%s%n", Integer.parseInt(overzicht[1]), Integer.parseInt(overzicht[1]) == 1 ? "" : "en");
        uitvoer += String.format("aantal sterren: %s%n", overzicht[2]);
        uitvoer += String.format("aantal spellen tot volgende ster: %s%n", overzicht[3]);
        System.out.println(uitvoer);
    }

}
