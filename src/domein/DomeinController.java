 package domein;

import exceptions.MoeilijkSpelToegangException;
import exceptions.NormaalSpelToegangException;
import exceptions.SpelersnaamWachtwoordCombinatieException;
import exceptions.WachtwoordBevestigingFoutException;
import java.util.Arrays;

/**
 * Bevat de methodes die zullen worden aangeroepn in de startApplicatie. *
 */
public class DomeinController {
    
    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler deSpeler;
    private Spel spel;
    
    //constructors
    /**
     * Class constructor. Creërt een object van SpelerRepository en een object
     * van SpelRepository. *
     * @param resourceBundle
     */
    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
    }

    //UC1   
    //operaties
    /**
     * Meld de speler (krijgt het spelerobjectje van spelerRepository) aan
     * indien spelersnaam en wachtwoord correct zijn.
     *
     * @param spelersnaam de spelersnaam van de gebruiker
     * @param wachtwoord het wachtwoord van de gebruiker
     */
    public void meldAan(String spelersnaam, String wachtwoord) {
        Speler gevondenSpeler = spelerRepository.geefSpeler(spelersnaam, wachtwoord);       
        if (gevondenSpeler != null) {                                                       
            setDeSpeler(gevondenSpeler);
        }else{
            throw new SpelersnaamWachtwoordCombinatieException();
        }
    }

    /**
     * Maakt een nieuw object van Speler aan met de spelersnaam en wachtwoord
     * als parameter. Daarna voegt men dit spelerobjectje toe aan de
     * spelerRepository
     *
     * @param spelersnaam spelersnaam die de gebruiker wil gebruiken
     * @param wachtwoord wachtwoord van de speler
     * @param bevestiging bevestiging van het wachtwoord (moet identiek zijn aan
     * wachtwoord)
     */
    public void registreer(String spelersnaam, String wachtwoord, String bevestiging) {
        Speler nieuweSpeler = new Speler(spelersnaam, wachtwoord);
        //wachtwoord moet gelijk zijn aan bevestiging
        if (wachtwoord.equals(bevestiging)) {
            spelerRepository.voegSpelerToe(nieuweSpeler);           //nog exception nodig
        }else{
            throw new WachtwoordBevestigingFoutException();
        }
        deSpeler = nieuweSpeler;
    }

    /**
     * Geeft de spelersnaam van de aangemelde speler terug.
     *
     * @return
     */
    public String geefSpelersnaam() {                    
        return deSpeler.getSpelersnaam();
    }

    //UC2
    /**
     * Maakt een object van Spel aan afhankelijk van de gekozen
     * moeilijkheidsgraad.
     *
     * @param moeilijkheidsgraad
     */
    public void kiesMoeilijkheidsgraad(int moeilijkheidsgraad) {
        switch (moeilijkheidsgraad) {
            case 1:
                spel = new MakkelijkSpel();
                break;
            case 2:
                if (deSpeler.getAantalGewonnen()[0] < 20) {
                    throw new NormaalSpelToegangException();
                }
                spel = new NormaalSpel();
                break;
            case 3:
                if (deSpeler.getAantalGewonnen()[1] < 20) {
                    throw new MoeilijkSpelToegangException();
                }
                spel = new MoeilijkSpel();
                break;
        }
        deSpeler.setSpel(spel);
    }

    /**
     * Geeft een overzicht van alle mogelijke moeilijkheidsgraden en het aantal
     * overwinningen per moeilijkheidsgraad.
     *
     * @return
     */
    public String[][] startMasterMind() {
        String[][] overzicht;
        String[] moeilijkheidsgraden = {"makkelijk", "normaal", "moeilijk"};
        String x = String.format("%d %d %d", deSpeler.getAantalGewonnen()[0], deSpeler.getAantalGewonnen()[1], deSpeler.getAantalGewonnen()[2]);
        String[] aantal = x.split(" ");
        if (Integer.parseInt(aantal[0]) < 20) {
            overzicht = new String[1][2];

        } else if (Integer.parseInt(aantal[1]) < 20) {
            overzicht = new String[2][2];

        } else {
            overzicht = new String[3][2];

        }
        for (int i = 0; i < overzicht.length; i++) {
            overzicht[i][0] = String.format("%-10s", moeilijkheidsgraden[i]);
            overzicht[i][1] = String.format("%5s %5s", aantal[i], "WINS");
        }
        return overzicht;
    }

    /**
     * Geeft een overzicht van het spelbord terug.
     *
     * @return
     */
    public int[][] geefSpelbord() {
        Spelbord spelbord = spel.getSpelbord();
        return spelbord.geefOverzichtMetPinnen();
    }

    //UC3
    /**
     * Geeft een poging door.
     *
     * @param poging de ingevoerde poging van de speler.
     */
    public void geefPoging(int[] poging) {
        spel.getSpelbord().geefPoging(poging);
    }

    public int[] geefCode(String[] poging) {
        return spel.getSpelbord().getWillekeurigeCode();
    }

    public String[] geefOverzicht() {
        String[] overzicht = new String[4];
        overzicht[0] = Arrays.toString(spel.getSpelbord().getWillekeurigeCode());
        overzicht[1] = String.format("%d", spel.getSpelbord().getAantalPogingen());
        int[] aantalGewonnen = deSpeler.getAantalGewonnen();
        int getal = 0;
        switch (spel.getClass().getSimpleName()) {
            case "MakkelijkSpel":
                getal = aantalGewonnen[0];
                break;
            case "NormaalSpel":
                getal = aantalGewonnen[1];
                break;
            case "MoeilijkSpel":
                getal = aantalGewonnen[2];
                break;
        }

        if (getal >= 250) {
            overzicht[2] = "✩✩✩✩✩";
            overzicht[3] = "/";
        } else if (getal >= 100) {
            overzicht[2] = "✩✩✩✩";
            overzicht[3] = String.format("%d", 250 - getal);
        } else if (getal >= 50) {
            overzicht[2] = "✩✩✩";
            overzicht[3] = String.format("%d", 100 - getal);
        } else if (getal >= 20) {
            overzicht[2] = "✩✩";
            overzicht[3] = String.format("%d", 50 - getal);
        } else if (getal >= 10) {
            overzicht[2] = "✩";
            overzicht[3] = String.format("%d", 20 - getal);
        } else {
            overzicht[2] = "0";
            overzicht[3] = String.format("%d", 10 - getal);
        }

        return overzicht;
    }

    public void registreerSpel(String spelnaam) {
        spelRepository.registreerSpel(spelnaam, deSpeler.getSpelersnaam(), spel);
    }
    
    public String[] geefSpellen()
    {
        String[] spellenString;
        int teller = 0;
        
        spellenString = new String[spelRepository.geefSpellen(deSpeler.getSpelersnaam()).length];
        
        for (String spelnaam : spelRepository.geefSpellen(deSpeler.getSpelersnaam())){
            spellenString[teller] = spelnaam;
            teller++;
        }
        
        return spellenString;
    }
    
    public void verwijderSpel(int spelnaam)
    {
        //Spel spel = spelRepository.laadSpel(int spelnaam);
        spelRepository.verwijderSpel(spelnaam, deSpeler.getSpelersnaam());
        
    }
    
    public int[][] startUitdaging()
    {
        int[][] aantalGewonnenPerMoeilijkheid = new int[3][3];
        
        int teller = 0;
        int teller2 = 0;
        
        for (int graad : spel.geefMoeilijkheidsgraden()){
            aantalGewonnenPerMoeilijkheid[teller][3] = graad;
            
            teller++;
        }
        
        for (int score : deSpeler.getAantalGewonnen()){
            aantalGewonnenPerMoeilijkheid[3][teller2] = score;
            
            teller2++;
        }
        
        return aantalGewonnenPerMoeilijkheid;
    }
    
    public Speler kiesTegenspeler(String tegenspeler)
    {
        return spelerRepository.selectSpeler(tegenspeler);
    }

    //setters
    /**
     * Setter. Zorgt ervoor dat het attribuut deSpeler de waarde krijgt van de
     * parameter.
     *
     * @param deSpeler een object van Speler
     */
    private void setDeSpeler(Speler deSpeler) {          //Als deSpeler final is mag setter verdwijnen, maar geen zekerheid tot nu toe, == als uitloggen moet kunnen -> niet final, enkel aanmelden -> final
        this.deSpeler = deSpeler;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut spel de waarde krijgt van de
     * parameter.
     *
     * @param spel een object van Spel
     */
    private void setSpel(Spel spel) {
        this.spel = spel;
    }
}
