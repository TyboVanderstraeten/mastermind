/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import exceptions.KeuzemenuException;
import exceptions.MeldAanRegistreerKeuzeException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

//EXCEPTIONS DONE
public class UC1Applicatie {

    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;

    public UC1Applicatie(ResourceBundle resourcebundle, DomeinController domeinController) {
        this.resourceBundle = resourcebundle;
        this.domeinController = domeinController;
    }

    public final void start() {
        meldAanRegistreer();
        System.out.println("\n" + domeinController.geefSpelersnaam() + "\n");
        boolean geldig = false;
        do {
            try {
                switch (maakKeuze()) {
                    case 1:
                        UC2Applicatie uc2 = new UC2Applicatie(resourceBundle, domeinController);
                        uc2.start();
                        break;
                    case 2:
                        UC4Applicatie uc4 = new UC4Applicatie(resourceBundle, domeinController);
                        uc4.start();
                        break;
                    case 3:
                        UC5Applicatie uc5 = new UC5Applicatie(resourceBundle, domeinController);
                        uc5.start();
                        break;
                    case 4:
                        UC6Applicatie uc6 = new UC6Applicatie(resourceBundle, domeinController);
                        uc6.start();
                        break;
                    case 5:
                        UC7Applicatie uc7 = new UC7Applicatie(resourceBundle, domeinController);
                        uc7.start();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
            } catch (IllegalArgumentException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
            }
        } while (!geldig);

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
                        //Door met next() te werken ipv nextLine() kan spelernaam niet leeg zijn en hoeft dit niet extra opgevangen te worden!
                        String spelernaam = input.next();
                        System.out.print(resourceBundle.getString("wachtwoordMetVoorbeeld"));
                        String wachtwoord = input.next();
                        System.out.print(resourceBundle.getString("wachtwoordHerhaling"));
                        domeinController.registreer(spelernaam, wachtwoord, input.next());
                        break;
                    default:
                        throw new MeldAanRegistreerKeuzeException();
                }
                geldig = true;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("ongeldig"));
                input.nextLine();
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            } catch (RuntimeException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            }
        } while (!geldig);
        System.out.printf("%n%s %s%n", resourceBundle.getString("welkom"), domeinController.geefSpelersnaam());
    }

    private int maakKeuze() {
        Scanner input = new Scanner(System.in);
        System.out.println(resourceBundle.getString("maakKeuze"));
        System.out.printf("1: %s%n2: %s%n3: %s%n4: %s%n5: %s%n6: %s%n", resourceBundle.getString("startMastermind"), resourceBundle.getString("laadMastermind"), resourceBundle.getString("daagUit"), resourceBundle.getString("aanvaardUitdaging"), resourceBundle.getString("toonKlassementUitdagingen"),resourceBundle.getString("stoppen"));
        int keuze = input.nextInt();
        if (keuze < 1 || keuze > 6) {
            throw new KeuzemenuException();
        }
        return keuze;
    }
}
