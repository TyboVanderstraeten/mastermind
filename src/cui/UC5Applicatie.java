package cui;

import domein.DomeinController;
import exceptions.MoeilijkheidsgraadKeuzeException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UC5Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC5Applicatie(ResourceBundle resourceBundle, DomeinController domeinController) {
        this.domeinController = domeinController;
        this.resourceBundle = resourceBundle;
    }

    public void start() {
        kiesTegenspeler();
    }

    public void kiesTegenspeler() {
        Scanner input = new Scanner(System.in);
        String[] tegenspelerNamen = {};
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
        try {
            String tegenspelerNaam = input.next();
            domeinController.kiesTegenspeler(tegenspelerNaam);
            registreerSpel(tegenspelerNaam);
        } catch (NullPointerException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
            input.nextLine();
        }

    }

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
    
    
    private void registreerSpel(String tegenSpeler){
        Scanner input = new Scanner(System.in);
        System.out.println("Geef een naam aan uw spel: ");
        String spelnaam = input.next();  
        domeinController.registreerSpel(spelnaam);
        domeinController.spelIsUitdaging();
        domeinController.registreerSpel(tegenSpeler, spelnaam);
    }
}
