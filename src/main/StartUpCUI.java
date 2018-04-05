package main;

import cui.MastermindApplicatie;
import domein.DomeinController;
import java.util.Locale;
import java.util.ResourceBundle;

public class StartUpCUI {

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("talen.MessagesBundle", Locale.ROOT);
        DomeinController domeinController = new DomeinController(resourceBundle);
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
