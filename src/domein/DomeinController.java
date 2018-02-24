package domein;

public class DomeinController {

    private SpelerRepository spelerRepository;

    public DomeinController() {
        spelerRepository = new SpelerRepository();
    }

    public String meldAan(String spelersnaam, String wachtwoord) {
        return "";
    }

    public void registreer(String spelersnaam, String wachtwoord, String bevestiging) {
        Speler nieuweSpeler = new Speler(spelersnaam, wachtwoord);
        if (wachtwoord.equals(bevestiging)) {
            spelerRepository.voegSpelerToe(nieuweSpeler);
        }
    }
}
