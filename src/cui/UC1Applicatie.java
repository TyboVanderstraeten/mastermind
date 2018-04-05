/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import exceptions.MeldAanRegistreerKeuzeException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class UC1Applicatie {
    private final DomeinController domeinController;
    private final ResourceBundle resourceBundle;
    
    public UC1Applicatie(ResourceBundle resourcebundle, DomeinController domeinController){
        this.resourceBundle = resourcebundle;
        this.domeinController = domeinController;        
    }
    
    public final void start(){
        meldAanRegistreer();
        System.out.println("\nWelkom " + domeinController.geefSpelersnaam() + "\n");
        
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
                        System.out.print(resourceBundle.getString("wachtwoordMetVoorbeeld"));
                        String wachtwoord = input.next();
                        System.out.print(resourceBundle.getString("wachtwoordHerhaling"));
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
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            } catch (RuntimeException e) {
                System.out.println(resourceBundle.getString(e.getMessage()));
                input.nextLine();
            }
        } while (!geldig);
        System.out.println(domeinController.geefSpelersnaam());
    }
}
