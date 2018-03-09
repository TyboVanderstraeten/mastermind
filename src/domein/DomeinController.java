package domein;

public class DomeinController {

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler deSpeler;
    private Spel spel;

    //constructors
    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
    }

    //UC1   
    //operaties
    public void meldAan(String spelersnaam, String wachtwoord) {
        Speler gevondenSpeler = spelerRepository.geefSpeler(spelersnaam, wachtwoord);       //zal sws spelerobject teruggeven dus alleen de gevonden speler die niet gelijk is aan null wordt doorgegeven als deSpeler
        if (gevondenSpeler != null) {                                                       //niet zeker of wachtwoord ook moet meegegeven worden
            setDeSpeler(gevondenSpeler);
        }
    }

    public void registreer(String spelersnaam, String wachtwoord, String bevestiging) {
        Speler nieuweSpeler = new Speler(spelersnaam, wachtwoord);
        //wachtwoord moet gelijk zijn aan bevestiging
        if (wachtwoord.equals(bevestiging)) {
            spelerRepository.voegSpelerToe(nieuweSpeler);           //nog exception nodig
        }
        deSpeler = nieuweSpeler;
    }

    public String geefSpelersnaam() {                    //***apparte public methode die in applicatie wordt opgeroepen OF private methode die vanuit meldAan wordt opgeroepen?
        return deSpeler.getSpelersnaam();
    }
    //***Tybo = Ik denk dat het private (?public?) methode wordt die vanuit meldAan wordt 
    //aangeroepen want meldAan is (tot nu toe) enige klasse die spelersnaam retourneert!

    //UC2
    public void kiesMoeilijkheidsgraad(int moeilijkheidsgraad) {
        switch (moeilijkheidsgraad) {
            case 1:
                spel = new MakkelijkSpel(moeilijkheidsgraad);
                break;
            case 2:
                spel = new NormaalSpel(moeilijkheidsgraad);
                break;
            case 3:
                spel = new MoeilijkSpel(moeilijkheidsgraad);
                break;
        }
    }

    public String[][] startMasterMind() {
        String[][] overzicht;
        String[] moeilijkheidsgraden = {"makkelijk", "normaal", "moeilijk"};
        String x = String.format("%d %d %d", MakkelijkSpel.getAantalGewonnen(), NormaalSpel.getAantalGewonnen(), MoeilijkSpel.getAantalGewonnen());
        String[] aantal = x.split(" ");
        int guard;
        if (Integer.parseInt(aantal[0]) < 20) {
            overzicht = new String[1][2];

        } else if (Integer.parseInt(aantal[1]) < 20) {
            overzicht = new String[2][2];

        } else {
            overzicht = new String[3][2];

        }
        for (int i = 0; i < overzicht.length; i++) {
            overzicht[i][0] = String.format("%-10s",moeilijkheidsgraden[i]);
            overzicht[i][1] = String.format("%5s %5s", aantal[i], "WINS");
        }
        return overzicht;
    }

    public String[][] geefSpelbord() {
        Spelbord spelbord = spel.getSpelbord();
        return spelbord.geefOverzichtMetPinnen();
    }
    
    
    //UC3
        
    
    //setters
    private void setDeSpeler(Speler deSpeler) {          //Als deSpeler final is mag setter verdwijnen, maar geen zekerheid tot nu toe, == als uitloggen moet kunnen -> niet final, enkel aanmelden -> final
        this.deSpeler = deSpeler;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

}
