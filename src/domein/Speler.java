package domein;

/**
 * Zorgt voor de interactie met de speler.
 *
 */
public class Speler {

    private String spelersnaam;
    private String wachtwoord;
    private int[] aantalGewonnen = {0, 0, 0};
    private Spel spel;
    private int aantalSterren = 0;

    //constructors
    /**
     * Class constructor. roept de setters aan van spelersnaam en wachtwoord.
     *
     * @param spelersnaam de spelersnaam van de speler
     * @param wachtwoord het wachtwoord van de speler
     * @see #setSpelersnaam(java.lang.String)
     * @see #setWachtwoord(java.lang.String)
     */
    public Speler(String spelersnaam, String wachtwoord) {
        setSpelersnaam(spelersnaam);
        setWachtwoord(wachtwoord);

    }

    //getters
    /**
     * Getter geeft het attribuut spelersnaam terug.
     *
     * @return
     */
    public String getSpelersnaam() {
        return spelersnaam;
    }

    /**
     * Getter geeft het attribuut wachtwoord terug.
     *
     * @return
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * Getter geeft het attribuut aantalGewonnen terug.
     *
     * @return
     */
    public int[] getAantalGewonnen() {
        return aantalGewonnen;
    }

    //setters
    /**
     * Setter zorgt ervoor dat het attribuut spelersnaam de waarde krijgt van de
     * parameter.
     *
     * @param spelersnaam spelersnaam van de gebruiker
     */
    private void setSpelersnaam(String spelersnaam) {
        if (spelersnaam.length() > 40 || spelersnaam == null) {
            throw new IllegalArgumentException("Spelersnaam mag max. 40 karakters bevatten en mag niet leeg zijn!");
        }
        this.spelersnaam = spelersnaam;
    }

    /**
     * Setter zorgt ervoor dat het attribuut wachtwoord de waarde krijgt van de
     * parameter.
     *
     * @param wachtwoord wachtwoord van de gebruiker
     */
    private void setWachtwoord(String wachtwoord) {
        if (wachtwoord.length() < 8 || wachtwoord.length() > 25) {
            throw new IllegalArgumentException("Wachtwoord moet 8-25 karakters bevatten (grenzen inbegrepen)!");
        }
        this.wachtwoord = wachtwoord;
    }
    
    public void setSpel(Spel spel){
        this.spel = spel;
    }

}
