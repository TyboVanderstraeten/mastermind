package main;

import cui.MastermindApplicatie;
import domein.DomeinController;

public class StartUpCUI {

    public static void main(String[] args) {        
        DomeinController domeinController = new DomeinController();
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
