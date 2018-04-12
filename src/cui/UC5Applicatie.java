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
        kiesMoeilijkheidsgraad();
        System.out.println(resourceBundle.getString("maakKeuzeTegenspeler"));       
        String[] tegenspelerNamen = domeinController.geefTegenSpelers(moeilijkheidsgraad);
        for (int teller = 0; teller < tegenspelerNamen.length; teller++) {
            System.out.printf("%d) %s%n", teller + 1, tegenspelerNamen[teller]);
        }
        try {
            String tegenspelerNaam = input.next();
            domeinController.kiesTegenspeler(tegenspelerNaam);
        } catch (NullPointerException e) {
            System.out.println(resourceBundle.getString(e.getMessage()));
            input.nextLine();
        }

    }
    
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
}
