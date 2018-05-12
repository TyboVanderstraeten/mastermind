package main;

import cui.MastermindApplicatie;
import domein.DomeinController;

/**
 * Startup van de cuiApplicatie.
 * 
 */
public class StartUpCUI {

    /**
     * main methode die een nieuw object van DomeinController aanmaakt en
     * dit meegeeft aan een nieuw object van MastermindApplicatie.
     * De cuiApplicatie wordt hier gestart.
     * 
     * @param args default parameter bij main.
     */
    public static void main(String[] args) {
        DomeinController domeinController = new DomeinController();
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
