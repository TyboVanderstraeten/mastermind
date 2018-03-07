/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author bramd
 */
public class MastermindApplicatie {

    private final DomeinController domeinController;

    public MastermindApplicatie(DomeinController domeinController) {
        this.domeinController = domeinController;
    }

    public void startApplicatie() {
        Scanner input = new Scanner(System.in);         //moet aangepast worden, gwn gebruikt om te testen.
        
        //  SPELBORD TERUGGEVEN
        domeinController.kiesMoeilijkheidsgraad(1);
        String[][] spelbord = domeinController.geefSpelbord();
        for(String[] x: spelbord){
            System.out.println(Arrays.toString(x));
        }
        
        //  OVERZICHT TERUGGEVEN
        String[][] overzicht = domeinController.startMasterMind();
        for(String[] a: overzicht){
        System.out.println(Arrays.toString(a));
        }
        
        
        //  AANMELDEN/REGISTREREN
        System.out.println("Wenst u zich aan te melden, of te registreren?\n1: MELD AAN\n2: REGISTREER");
        switch (input.nextInt()) {

            case 1:
                System.out.print("Geef uw spelernaam: ");
                String spelersnaam = input.next();
                System.out.print("Geef uw wachtwoord: ");
                domeinController.meldAan(spelersnaam, input.next());
                break;
            case 2:
                System.out.print("geef een geldige spelernaam: ");
                String spelernaam = input.next();
                System.out.print("Geef een wachtwoord: ");
                String wachtwoord = input.next();
                System.out.print("Geef uw wachtwoord nogmaals ter bevestiging: ");
                System.out.println("");
                domeinController.registreer(spelernaam, wachtwoord, input.next());
                break;
        }
        System.out.println(domeinController.geefSpelersnaam());

    }
}
