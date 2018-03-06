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
            spelerRepository.voegSpelerToe(nieuweSpeler);
        }
    }

    public String geefSpelersnaam() {                    //***apparte public methode die in applicatie wordt opgeroepen OF private methode die vanuit meldAan wordt opgeroepen?
        return deSpeler.getSpelersnaam();
    }
    //***Tybo = Ik denk dat het private (?public?) methode wordt die vanuit meldAan wordt 
    //aangeroepen want meldAan is (tot nu toe) enige klasse die spelersnaam retourneert!

    public void kiesMoeilijkheidsgraad(int moeilijkheidsgraad) {
        switch(moeilijkheidsgraad){
            case 1: spel = new MakkelijkSpel();
            break;
            case 2: spel = new NormaalSpel();
            break;
            case 3: spel = new MoeilijkSpel();
            break;            
        }
    }

    public String[][] startMasterMind() {
        return spelRepository.startMasterMind();
    }

    public String[][] geefSpelbord() {
        Spelbord spelbord = spel.getSpelbord();
        return spelbord.geefOverzichtMetPinnen();
    }

    //setters
    public void setDeSpeler(Speler deSpeler) {          //Als deSpeler final is mag setter verdwijnen, maar geen zekerheid tot nu toe, == als uitloggen moet kunnen -> niet final, enkel aanmelden -> final
        this.deSpeler = deSpeler;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

    
}
