package cui;

import domein.DomeinController;
import exceptions.MoeilijkheidsgraadKeuzeException;
import exceptions.TegenspelerNaamBestaatNietException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//EXCEPTIONS DONE
/**
 * Bevat het volledige verloop van UC5.
 * @author bramd
 */
public class UC5Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    /**
     * Class constructor.
     * Zorgt ervoor dat de domeinController en resourceBundle geïnitialiseert worden.
     * 
     * @param resourceBundle het Object van de ResourceBundle.
     * @param domeinController het Object van de DomeinController.
     */
    public UC5Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    /**
     * Bevat het volledige verloop van UC5.
     */
    public void start() {
        try {
            if (domeinController.geefOpenUitdagingen() != null) {
                System.out.println("Werk uw openstaande uitdaging eerst af!");
            } else {
                registreerSpel(kiesTegenspeler());
                toonSpelbord();
                UC3Applicatie uc3 = new UC3Applicatie(resourceBundle, domeinController);
                uc3.start();
            }
        } catch (NullPointerException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
        }
    }

    /**
     * Bevat het volledige verloop om een tegenspeler te kiezen.
     * @return  een String die de naam van de tegenspeler bevat.
     */
    public String kiesTegenspeler() {
        Scanner input = new Scanner(System.in);
        String[] tegenspelerNamen = {};
        String tegenspelerNaam;
        switch (kiesMoeilijkheidsgraad()) {
            case 1:
                tegenspelerNamen = domeinController.geefTegenSpelers("aantalGewonnenUitdagingenMakkelijk", 0);
                break;
            case 2:
                tegenspelerNamen = domeinController.geefTegenSpelers("aantalGewonnenUitdagingenMakkelijk", 20);
                break;
            case 3:
                tegenspelerNamen = domeinController.geefTegenSpelers("aantalGewonnenUitdagingenNormaal", 20);
                break;
        }

        System.out.println(resourceBundle.getString("maakKeuzeTegenspeler"));
        for (int teller = 0; teller < tegenspelerNamen.length; teller++) {
            System.out.printf("%d) %s%n", teller + 1, tegenspelerNamen[teller]);
        }
        tegenspelerNaam = input.next();
        if (!Arrays.asList(tegenspelerNamen).contains(tegenspelerNaam)) {
            throw new TegenspelerNaamBestaatNietException();
        }

        return tegenspelerNaam;

    }

    /**
     * Bevat het volledige verloop om een moeilijkheidsgraad te kiezen.
     * 
     * @return een int die de moeilijkheidsgraad representeert.
     * 1: makkelijk
     * 2: normaal
     * 3: moeilijk
     */
    private int kiesMoeilijkheidsgraad() {
        Scanner input = new Scanner(System.in);
        boolean geldig = false;
        int keuze = 0;
        do {
            try {
                System.out.println("\n" + resourceBundle.getString("keuzeMoeilijkheid"));
                System.out.println("1: " + resourceBundle.getString("makkelijkeMoeilijkheidsgraad"));
                System.out.println("2: " + resourceBundle.getString("normaleMoeilijkheidsgraad"));
                System.out.println("3: " + resourceBundle.getString("moeilijkeMoeilijkheidsgraad"));
                keuze = input.nextInt();
                if (keuze < 1 || keuze > 3) {
                    throw new MoeilijkheidsgraadKeuzeException();
                }
                domeinController.kiesMoeilijkheidsgraadUitdagingen(keuze);
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            }
        } while (!geldig);
        return keuze;
    }

    /**
     * Bevat het volledige verloop om een uitdaging te registreren.
     * 
     * @param tegenSpeler de naam van de tegenspeler.
     */
    private void registreerSpel(String tegenSpeler) {
        domeinController.registreerUitdaging(tegenSpeler);
        domeinController.laadUitdaging(domeinController.geefSpelersnaam());

    }

    /**
     * Bevat het volledige verloop om een spelbord op het scherm te tonen.
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
