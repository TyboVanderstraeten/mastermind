package cui;

import domein.DomeinController;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * De applicatie zelf,de verschillende handelingen worden in de juiste volgorde
 * uitgevoerd.
 *
 */
public class MastermindApplicatie {

    private final DomeinController domeinController;

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
        Scanner input = new Scanner(System.in);         //moet aangepast worden, gwn gebruikt om te testen.
        //NIET IN VOLGORDE ETC.
        boolean geldig = false;
        // Try catch binnen de try catch vermijden! Anders oplossen :) (Voor opnieuw vragen spelersnaam (apart), wachtwoord (apart),...
        //  AANMELDEN/REGISTREREN
        do {
            try {
                System.out.println("Wenst u zich aan te melden, of te registreren?\n1: MELD AAN\n2: REGISTREER");
                switch (input.nextInt()) {

                    case 1:
                        System.out.print("Geef uw spelernaam: ");
                        String spelersnaam = input.next();
                        System.out.print("Geef uw wachtwoord: ");
                        domeinController.meldAan(spelersnaam, input.next());
                        break;
                    case 2:
                        System.out.print("Geef een geldige spelernaam: ");
                        String spelernaam = input.next();
                        System.out.print("Geef een wachtwoord: ");
                        String wachtwoord = input.next();
                        System.out.print("Geef uw wachtwoord nogmaals ter bevestiging: ");
                        System.out.println("");
                        domeinController.registreer(spelernaam, wachtwoord, input.next());
                        break;
                }
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer!");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!geldig);
        System.out.println(domeinController.geefSpelersnaam());

        //  SPELBORD TERUGGEVEN
        System.out.println("Welke moeilijkheidsgraad wenst u te spelen? (geef het nummer)\n1: makkelijk\n2: normaal\n3: moeilijk");
        domeinController.kiesMoeilijkheidsgraad(input.nextInt());
        String[][] spelbord = domeinController.geefSpelbord();
        for (String[] x : spelbord) {
            System.out.println(Arrays.toString(x).replace(",", " ").replace("[", "| ").replace("]", " |"));
        }

        //  OVERZICHT TERUGGEVEN
        String[][] overzicht = domeinController.startMasterMind();
        for (String[] a : overzicht) {
            System.out.println(Arrays.toString(a));
        }

    }
}
