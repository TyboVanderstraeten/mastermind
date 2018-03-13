/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cui.MastermindApplicatie;
import domein.DomeinController;

/**
 *  Bevat de main. 
 * 
 * 
 */
public class startUp {
    /**
     * maakt een object van de DomeinController en geeft deze mee aan de applicatie.
     * de applicatie wordt dan gestart.
     * 
     * @param args  standaard parameter van de main methode
     */
    public static void main(String[] args) {
        DomeinController domeinController = new DomeinController();
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
