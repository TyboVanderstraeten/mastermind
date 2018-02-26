package domein;

public class Speler {

    private String spelersnaam;
    private String wachtwoord;

    //constructors
    public Speler(String spelersnaam, String wachtwoord) {
        setSpelersnaam(spelersnaam);
        setWachtwoord(wachtwoord);
    }

    //getters
    public String getSpelersnaam() {
        return spelersnaam;
    }

    //setters
    private void setSpelersnaam(String spelersnaam) {
        this.spelersnaam = spelersnaam;
    }

    private void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

}
