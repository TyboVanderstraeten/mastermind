package cui;

import domein.DomeinController;
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
        while (domeinController.geefSpelbord()[domeinController.geefSpelbord().length - 1][0].contains("#     ")) {
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
                        throw new IllegalArgumentException("ongeldige keuze.");
                }
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!geldig);
        System.out.println(domeinController.geefSpelersnaam());
    }

    private void geefTaal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your language: (enter the number) \n1: Nederlands \n2: Français \n3: English");

        switch (input.nextInt()) {
            case 1:
                resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
                break;
            case 2:
                resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
                break;
            case 3:
                resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);
                break;
            default:
                resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
                break;
        }
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
        String[][] spelbord = domeinController.geefSpelbord();
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "wit", "zwart"};
        for (String[] x : spelbord) {
//            for(String a : x){                
//                 a = resourceBundle.getString(kleuren[Integer.parseInt(a)]);                
//            }
            if (spelbord.length == 13 && x == spelbord[spelbord.length - 1]) {
                x[x.length / 2] = String.format("\t\t%10s", " ");
                for (int i = x.length / 2 + 1; i < x.length; i++) {   //x.length/2 is 4 (makkelijk/normaal) of 5 (moeilijk)
                    x[i] = String.format("%6s", " ");
                }
            }
            System.out.println(Arrays.toString(x).replace(",", " ").replace("[", "| ").replace("]", " |"));

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

    private void doePoging() {
        Scanner input = new Scanner(System.in);
        String[] poging = new String[domeinController.geefSpelbord()[0].length == 9 ? 4 : 5];
        String[] kleuren = {resourceBundle.getString("blauw"), resourceBundle.getString("groen"), resourceBundle.getString("rood"), resourceBundle.getString("paars"), resourceBundle.getString("geel"), resourceBundle.getString("oranje"), resourceBundle.getString("bruin"), resourceBundle.getString("grijs")};
        System.out.printf("%n%s%n%s%n", resourceBundle.getString("kleurIngevenD1"), resourceBundle.getString("kleurIngevenD2"));
        for (int i = 0; i < poging.length; i++) {
            poging[i] = input.next();
            if (!Arrays.asList(kleuren).contains(poging[i])) {
                System.out.println("Ongeldige kleur");
                i--;
            }
        }
        domeinController.geefPoging(poging);
    }

    private void geefEindoverzicht() {
        String uitvoer = "";
        String[] overzicht = domeinController.geefOverzicht();
        uitvoer += String.format("De code was: %s%n", overzicht[0].replace(",", " ").replace("[", "").replace("]", ""));
        uitvoer += String.format("Gekraakt in %d poging%s%n", Integer.parseInt(overzicht[1]), Integer.parseInt(overzicht[1]) == 1 ? "" : "en");
        uitvoer += String.format("aantal sterren: %s%n", overzicht[2]);
        uitvoer += String.format("aantal spellen tot volgende ster: %s%n", overzicht[3]);
        System.out.println(uitvoer);
    }

}
