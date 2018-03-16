package main;

import cui.MastermindApplicatie;
import domein.DomeinController;
import java.util.Locale;
import java.util.ResourceBundle;

public class StartUpCUI {

    public static void main(String[] args) {
        
        String language;
        
        if (args.length != 2){
            language = new String("nl");
        }else{
            language = new String(args[0]);
        }
        
        Locale currenLocale;
        ResourceBundle messages;
        
        currenLocale = new Locale(language);
        
        messages = ResourceBundle.getBundle("MessagesBundle", currenLocale);
        
        DomeinController domeinController = new DomeinController();
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
