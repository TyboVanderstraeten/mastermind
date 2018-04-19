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
    private int[] aantalGewonnen = {0, 0, 0};
    private int[] aantalGewonennUitdagingen = {0, 0, 0};
    private Spel spel;
    private int[] score = {0, 0, 0};

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

    public Speler(String spelersnaam, String wachtwoord, int[] aantalGewonnen, int[] aantalGewonnenUitdagingen) {
        setSpelersnaam(spelersnaam);
        setWachtwoord(wachtwoord);
        setAantalGewonnen(aantalGewonnen);
        setAantalGewonnen(aantalGewonnenUitdagingen);
    }

    public Speler selectSpeler(String tegenspeler) {
        return null;
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

    public int[] getAantalGewonnenUitdagingen() {
        return aantalGewonennUitdagingen;
    }

    //setters
    /**
     * Setter zorgt ervoor dat het attribuut spelersnaam de waarde krijgt van de
     * parameter.
     *
     * @param spelersnaam spelersnaam van de gebruiker
     */
    private void setSpelersnaam(String spelersnaam) {
        if (spelersnaam.length() > 40 || spelersnaam.isEmpty() || spelersnaam == null) {
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

//    public void setScore(int makkelijk, int normaal, int moeilijk)
//    {
//        this.score = int[makkelijk, normaal, moeilijk];
//    }
    public void setSpel(Spel spel) {
        this.spel = spel;
    }

    private void setAantalGewonnen(int[] aantalGewonnen) {
        this.aantalGewonnen = aantalGewonnen;
    }

    private void setAantalGewonnenUitdagingen(int[] aantalGewonnenUitdagingen) {
        this.aantalGewonennUitdagingen = aantalGewonnenUitdagingen;
    }

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

}
