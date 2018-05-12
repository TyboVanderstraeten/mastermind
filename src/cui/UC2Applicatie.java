package cui;

import domein.DomeinController;
import exceptions.MoeilijkheidsgraadKeuzeException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
/**
 * Bevat het volledige verloop van UC2.
 *
 */
public class UC2Applicatie {

    private final ResourceBundle resourceBundle;
    private final DomeinController domeinController;

    /**
     * Class constructor.
     * Zorgt ervoor dat de domeinController en resourceBundle geïnitialiseert worden.
     * 
     * @param resourceBundle het Object van de ResourceBundle.
     * @param domeinController het Object van de DomeinController.
     */
    public UC2Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.resourceBundle = resourceBundle;
        this.domeinController = domeinController;
    }

    /**
     * bevat het volledige verloop van UC2.
     */
    public void start() {
        geefOverzicht();
        kiesMoeilijkheidsgraad();
        toonSpelbord();
        UC3Applicatie uc3 = new UC3Applicatie(resourceBundle, domeinController);
        uc3.start();
    }

    /**
     * Bevat he verloop om het overzicht op het scherm weer te geven.
     */
    private void geefOverzicht() {
        String[][] overzicht = domeinController.startMasterMind();

//        for (String[] a : overzicht) {
//            for (String x : a) {
//
//            }
//        }
        for (String[] a : overzicht) {
            System.out.println(Arrays.toString(a));
        }

    }

    /**
     * Bevat het verloop om de moeilijkheidsgraad te kiezen.
     */
    private void kiesMoeilijkheidsgraad() {
        Scanner input = new Scanner(System.in);
        boolean geldig = false;
        do {
            try {
                System.out.println("\n" + resourceBundle.getString("keuzeMoeilijkheid"));
                System.out.println("1: " + resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
                System.out.println("2: " + resourceBundle.getString("normaleMoeilijkheidsgraad"));
                System.out.println("3: " + resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
                int keuze = input.nextInt();
                if (keuze < 1 || keuze > 3) {
                    throw new MoeilijkheidsgraadKeuzeException();
                }
                domeinController.kiesMoeilijkheidsgraad(keuze);
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            }
        } while (!geldig);
    }

    /**
     * Bevat het verloop om het spelbord op het scherm te brengen.
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
