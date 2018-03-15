package domein;

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
        Speler gevondenSpeler = spelerRepository.geefSpeler(spelersnaam, wachtwoord);       //zal sws spelerobject teruggeven dus alleen de gevonden speler die niet gelijk is aan null wordt doorgegeven als deSpeler
        if (gevondenSpeler != null) {                                                       //niet zeker of wachtwoord ook moet meegegeven worden
            setDeSpeler(gevondenSpeler);
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
        }
        deSpeler = nieuweSpeler;
    }

    /**
     * Geeft de spelersnaam van de aangemelde speler terug.
     *
     * @return
     */
    public String geefSpelersnaam() {                    //***apparte public methode die in applicatie wordt opgeroepen OF private methode die vanuit meldAan wordt opgeroepen?
        return deSpeler.getSpelersnaam();
    }
    //***Tybo = Ik denk dat het private (?public?) methode wordt die vanuit meldAan wordt 
    //aangeroepen want meldAan is (tot nu toe) enige klasse die spelersnaam retourneert!

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
                    throw new IllegalArgumentException("Deze mogelijkheidsgraad is pas toegankelijk als u 20 makkelijke spellen heeft gewonnen.");
                }
                spel = new NormaalSpel();
                break;
            case 3:
                if (deSpeler.getAantalGewonnen()[1] < 20) {
                    throw new IllegalArgumentException("Deze mogelijkheidsgraad is pas toegankelijk als u 20 normale spellen heeft gewonnen.");
                }
                spel = new MoeilijkSpel();
                break;
        }
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
    public String[][] geefSpelbord() {
        Spelbord spelbord = spel.getSpelbord();
        return spelbord.geefOverzichtMetPinnen();
    }

    
    
    
    //UC3
    
    /**
     * Geeft een poging door.
     * @param poging de ingevoerde poging van de speler.
     */
    public void geefPoging(String[] poging) {
        spel.getSpelbord().geefPoging(poging);
    }

    
    public String[] geefCode(String[] poging){
        return spel.getSpelbord().getWillekeurigeCode();
    }
    
    private String[] geefOverzicht(){
        String[] overzicht = new String[4];
        
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
