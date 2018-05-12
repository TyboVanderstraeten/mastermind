package domein;

import exceptions.SpelersnaamException;
import exceptions.WachtwoordException;

/**
 * Zorgt voor de interactie met de speler.
 *
 */
public class Speler {

    private String spelersnaam;
    private String wachtwoord;
    private int[] aantalGewonnen; //= {0, 0, 0};
    private int[] aantalGewonnenUitdagingen; //= {0, 0, 0};
    //private int[] aantalPunten; //= {0, 0, 0};
    private int[] aantalGespeeldeUitdagingen; // = {0, 0, 0};
    private Spel spel;

    //constructors
    /**
     * Class constructor. Wordt aangeroepen als er een nieuwe speler wordt
     * geregistreerd. Roept de andere constructor aan met default waarden 3 lege
     * arrays.
     *
     * @param spelersnaam de spelersnaam van de speler
     * @param wachtwoord het wachtwoord van de speler
     * @see #setSpelersnaam(java.lang.String)
     * @see #setWachtwoord(java.lang.String)
     */
    public Speler(String spelersnaam, String wachtwoord) {
        this(spelersnaam, wachtwoord, new int[3], new int[3], new int[3]);
//        setSpelersnaam(spelersnaam);
//        setWachtwoord(wachtwoord);
    }

    /**
     * Class constructor. Wordt aangeroepen als er een speler aangemeld wordt.
     * Zal de setters van de attributen aanroepen zodat deze de juiste waarde
     * krijgen.
     *
     * @param spelersnaam de naam van de speler.
     * @param wachtwoord het wachtwoord van de speler.
     * @param aantalGewonnen het aantal gewonnen spellen van de speler.
     * @param aantalGewonnenUitdagingen het aantal gewonnen uitdagingen van de
     * speler.
     * @param aantalGespeeldUitdagingen het aantal gespeelde uitdagingen van de
     * speler.
     */
    public Speler(String spelersnaam, String wachtwoord, int[] aantalGewonnen, int[] aantalGewonnenUitdagingen, int[] aantalGespeeldUitdagingen) {
        setSpelersnaam(spelersnaam);
        setWachtwoord(wachtwoord);
        setAantalGewonnen(aantalGewonnen);
        setAantalGewonnenUitdagingen(aantalGewonnenUitdagingen);
        //setAantalPunten(aantalPunten);
        setAantalGespeeldeUitdagingen(aantalGespeeldUitdagingen);
    }

    //Methode om het aantal gewonnen (spellen geen uitdagingen) spellen te verhogen
    /**
     * Methode die het aantal gewonnen spellen zal verhogen van de juiste
     * moeilijkheidsgraad.
     */
    public void verhoogAantalGewonnen() {
        switch (spel.getClass().getSimpleName()) {
            case "MakkelijkSpel":
                aantalGewonnen[0]++;
                break;
            case "NormaalSpel":
                aantalGewonnen[1]++;
                break;
            case "MoeilijkSpel":
                aantalGewonnen[2]++;
                break;
        }
    }

    /**
     * Methode die het aantal gewonnen uitdagingen zal verhogen voor de juiste
     * moeilijkheidsgraad.
     */
    public void verhoogAantalGewonnenUitdagingen() {
        switch (spel.getClass().getSimpleName()) {
            case "MakkelijkSpel":
                aantalGewonnenUitdagingen[0]++;
                break;
            case "NormaalSpel":
                aantalGewonnenUitdagingen[1]++;
                break;
            case "MoeilijkSpel":
                aantalGewonnenUitdagingen[2]++;
                break;
        }
    }

    /**
     * Methode die het aantal gespeelde uitdagingen zal verhogen voor de juiste
     * moeilijkheidsgraad.
     */
    public void verhoogAantalGespeeldeUitdagingen() {
        switch (spel.getClass().getSimpleName()) {
            case "MakkelijkSpel":
                aantalGespeeldeUitdagingen[0]++;
                break;
            case "NormaalSpel":
                aantalGespeeldeUitdagingen[1]++;
                break;
            case "MoeilijkSpel":
                aantalGespeeldeUitdagingen[2]++;
                break;
        }
    }

    //getters
    /**
     * Getter geeft het attribuut spelersnaam terug.
     *
     * @return de naam van de speler.
     */
    public String getSpelersnaam() {
        return spelersnaam;
    }

    /**
     * Getter geeft het attribuut wachtwoord terug.
     *
     * @return het wachtwoord van de speler.
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * Getter geeft het attribuut aantalGewonnen terug.
     *
     * @return het aantal gewonnen spellen.
     */
    public int[] getAantalGewonnen() {
        return aantalGewonnen;
    }

    /**
     * Getter. Geeft het aantal gewonnen uitdagingen terug.
     *
     * @return aantal gewonnen uitdagingen.
     */
    public int[] getAantalGewonnenUitdagingen() {
        return aantalGewonnenUitdagingen;
    }

    /**
     * Getter. Geeft het aantal gespeelde uitdagingen terug.
     *
     * @return aantal gespeelde uitdagingen.
     */
    public int[] getAantalGespeeldUitdagingen() {
        return aantalGespeeldeUitdagingen;
    }

    //setters
    /**
     * Setter zorgt ervoor dat het attribuut spelersnaam de waarde krijgt van de
     * parameter.
     *
     * @param spelersnaam spelersnaam van de gebruiker
     */
    private void setSpelersnaam(String spelersnaam) {
        if (spelersnaam.length() > 40 || spelersnaam.isEmpty()) {
            throw new SpelersnaamException();
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
        String wachtwoordVoorwaarden = "[0-9][a-zA-Z]{6}[a-zA-Z]*[0-9]";
        if (!(wachtwoord.matches(wachtwoordVoorwaarden))) {
            throw new WachtwoordException();
        }

        this.wachtwoord = wachtwoord;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut spel de waarde krijgt van de
     * paramter spel.
     *
     * @param spel het spel object dat de speler aan het spelen is.
     */
    public void setSpel(Spel spel) {
        this.spel = spel;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut aantalGewonnen de waarde krijgt
     * van de parameter aantalGewonnen.
     *
     * @param aantalGewonnen het aantal gewonnen spellen van de speler.
     */
    private void setAantalGewonnen(int[] aantalGewonnen) {
        this.aantalGewonnen = aantalGewonnen;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut aantalGewonnenUitdagingen de
     * waarde krijgt van de parameter aantalGewonnenUitdagingen.
     *
     * @param aantalGewonnenUitdagingen het aantal gewonnen uitdagingen van de
     * speler.
     */
    private void setAantalGewonnenUitdagingen(int[] aantalGewonnenUitdagingen) {
        this.aantalGewonnenUitdagingen = aantalGewonnenUitdagingen;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut aantalGespeelde uitdagingen de
     * waarde krijgt van de parameter aantalGespeeldeUitdagingen.
     *
     * @param aantalGespeeldeUitdagingen het aantal gespeelde uitdagingen van de
     * speler.
     */
    private void setAantalGespeeldeUitdagingen(int[] aantalGespeeldeUitdagingen) {
        this.aantalGespeeldeUitdagingen = aantalGespeeldeUitdagingen;
    }

}
