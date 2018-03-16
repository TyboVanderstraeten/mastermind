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
        boolean geldig1 = false;
        
        ResourceBundle resourceBundle;
        
        resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
//      resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.FRANCE);
//      resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ENGLISH);
        
        // Try catch binnen de try catch vermijden! Anders oplossen :) (Voor opnieuw vragen spelersnaam (apart), wachtwoord (apart),...
        //  AANMELDEN/REGISTREREN
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
                }
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!geldig);
        System.out.println(domeinController.geefSpelersnaam());

        //  SPELBORD TERUGGEVEN
        do {
            try {
                System.out.println(resourceBundle.getString("keuzeMoeilijkheid"));
                System.out.println("1: " + resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
                System.out.println("2: " + resourceBundle.getString("normaleMoeilijkheidsgraad"));
                System.out.println("3: " + resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
                domeinController.kiesMoeilijkheidsgraad(input.nextInt());
                geldig1 = true;
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!geldig1);

        String[][] spelbord = domeinController.geefSpelbord();
        for (String[] x : spelbord) {
            System.out.println(Arrays.toString(x).replace(",", " ").replace("[", "| ").replace("]", " |"));
        }

        //  OVERZICHT TERUGGEVEN
        String[][] overzicht = domeinController.startMasterMind();
        for (String[] a : overzicht) {
            System.out.println(Arrays.toString(a));
        }
        
        //Poging indienen
        String[] poging= new String[domeinController.geefSpelbord().length]; ///verkeerd, will change
        for(int i = 0; i<poging.length; i++){
            System.out.printf("%s %d %s%n%s", resourceBundle.getString("kleurIngevenD1"), i+1, resourceBundle.getString("kleurIngevenD2"), resourceBundle.getString("kleurIngevenD3"));
            poging[i] = input.next();
        }
        domeinController.geefPoging(poging);
        
        String[][] spelbord2 = domeinController.geefSpelbord();
        for (String[] x : spelbord) {
            System.out.println(Arrays.toString(x).replace(",", " ").replace("[", "| ").replace("]", " |"));
        }
        

    }
}
