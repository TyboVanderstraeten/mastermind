package domein;

public class DomeinController {

    private final SpelerRepository spelerRepository;
    private Speler deSpeler;

    //constructors
    public DomeinController() {
        spelerRepository = new SpelerRepository();
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

    public String geefSpelersnaam() {                    //apparte public methode die in applicatie wordt opgeroepen OF private methode die vanuit meldAan wordt opgeroepen?
        return deSpeler.getSpelersnaam();
    }

    //setters
    public void setDeSpeler(Speler deSpeler) {          //Als deSpeler final is mag setter verdwijnen, maar geen zekerheid tot nu toe
        this.deSpeler = deSpeler;
    }

}
