package domein;

import java.util.List;
import persistentie.SpelerMapper;

/**
 * Zorgt voor de verbinding met de SpelerMapper uit de persistentielaag.
 *
 */
public class SpelerRepository {

    private final SpelerMapper mapper;

    //constructors
    /**
     * Class constructor. roept de constructor van Spelermapper op en bewaardt
     * dit object als attribuut.
     */
    public SpelerRepository() {
        mapper = new SpelerMapper();
    }

    //operaties
    /**
     * Geeft een spelerobject weer indien spelersnaam en wachtwoord correct
     * zijn.
     *
     * @param spelersnaam spelersnaam van de gebruiker.
     * @param wachtwoord wachtwoord van de gebruiker.
     * @return Het object Speler van de speler met de juiste spelersnaam en
     * wachtwoord.
     */
    public Speler geefSpeler(String spelersnaam, String wachtwoord) {
        Speler speler = mapper.geefSpeler(spelersnaam);
        if (speler.getWachtwoord().equals(wachtwoord)) {
            return speler;
        }
        return null;
    }

    /**
     * Zal de mapper aanroepen om zo het Speler object van de tegenspeler aan te
     * roepen. Zo kan het aantalGewonnen verhoogt worden.
     *
     * @param spelersnaam de naam van de tegenspeler.
     * @return Het object van de tegenspeler.
     */
    public Speler geefTegenSpeler(String spelersnaam) {
        return mapper.geefSpeler(spelersnaam);
    }
    //aanpassingen in mapperklasse nodig voor geefSpeler(..) en voegSpelerToe(..)

    /**
     * Voegt een spelerobject toe aan de databank. (de speler wordt meegegeven
     * met de mapper die het uiteindelijk zal toevoegen.)
     *
     *
     * @param speler een object van Speler
     */
    public void voegSpelerToe(Speler speler) {
        this.mapper.voegSpelerToe(speler);
    }

    /**
     * Zal de mapper aanroepen om de mogelijke tegenspelers voor een uitdaging
     * van een bepaalde moeilijkheidsgraad terug te geven. De List wordt hier
     * ook omgezet naar een array.
     *
     * @param naamUitdagingenCategorie de moeilijkheidsgraad van de uitdaging.
     * @param aantalGewonnenUitdagingen het aantal gewonnen uitdagingen die de
     * tegenspeler moet hebben.
     * @param spelersnaam de spelersnaam van de huidige speler.
     *
     * @return een String[] die de namen van de tegenspelers bevat.
     */
    public String[] geefTegenspelers(String naamUitdagingenCategorie, int aantalGewonnenUitdagingen, String spelersnaam) {
        List<String> tegenSpelers = mapper.geefTegenspelers(naamUitdagingenCategorie, aantalGewonnenUitdagingen, spelersnaam);
        String[] tegenspelerNamen = new String[tegenSpelers.size()];
        int teller = 0;

        for (String tegenspelerNaam : tegenSpelers) {
            tegenspelerNamen[teller] = tegenspelerNaam;
            teller++;
        }

        return tegenspelerNamen;
    }

    /**
     * Zal het aantalGewonnenSpellen van de speler updaten.
     *
     * @param spelersnaam de spelersnaam
     * @param aantalGewonnenMakkelijk het aantal gewonnen makkelijke spellen.
     * @param aantalGewonnenNormaal het aantal gewonnen normale spellen.
     * @param aantalGewonnenMoeilijk het aantal gewonnen moeilijke spellen.
     */
    public void updateSpelerAantalGewonnen(String spelersnaam, int aantalGewonnenMakkelijk, int aantalGewonnenNormaal, int aantalGewonnenMoeilijk) {
        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalGewonnenMakkelijk, aantalGewonnenNormaal, aantalGewonnenMoeilijk);
    }

    /**
     * Zal het aantal gewonnen uitdagingen van de speler updaten.
     *
     * @param spelersnaam de spelersnaam.
     * @param aantalGewonnenUitdagingenMakkelijk het aantal gewonnen makkelijke
     * uitdagingen.
     * @param aantalGewonnenUitdagingenNormaal het aantal gewonnen normale
     * uitdagingen.
     * @param aantalGewonnenUitdagingenMoeilijk het aantal gewonnen moeilijke
     * uitdagingen.
     */
    public void updateAantalGewonnenUitdagingen(String spelersnaam, int aantalGewonnenUitdagingenMakkelijk, int aantalGewonnenUitdagingenNormaal, int aantalGewonnenUitdagingenMoeilijk) {
        mapper.updateAantalGewonnenUitdagingen(spelersnaam, aantalGewonnenUitdagingenMakkelijk, aantalGewonnenUitdagingenNormaal, aantalGewonnenUitdagingenMoeilijk);
    }

//    /**
//     * 
//     * @param spelersnaam
//     * @param aantalPuntenMakkelijk
//     * @param aantalPuntenNormaal
//     * @param aantalPuntenMoeilijk 
//     */
//    public void updateAantalPunten(String spelersnaam, int aantalPuntenMakkelijk, int aantalPuntenNormaal, int aantalPuntenMoeilijk) {
//        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalPuntenMakkelijk, aantalPuntenNormaal, aantalPuntenMoeilijk);
//    }
    /**
     * Zal het aantal gespeelde uitdagingen van de speler updaten.
     *
     * @param spelersnaam de naam van de speler.
     * @param aantalGespeeldeUitdagingenMakkelijk het aantal gespeelde
     * makkelijke uitdagingen.
     * @param aantalGespeeldeUitdagingenNormaal het aantal gespeelde normale
     * uitdagingen.
     * @param aantalGespeeldeUitdagingenMoeilijk het aantal gespeelde moeilijke
     * uitdagingen.
     */
    public void updateAantalGespeeldeUitdagingen(String spelersnaam, int aantalGespeeldeUitdagingenMakkelijk, int aantalGespeeldeUitdagingenNormaal, int aantalGespeeldeUitdagingenMoeilijk) {
        mapper.updateAantalGespeeldeUitdagingen(spelersnaam, aantalGespeeldeUitdagingenMakkelijk, aantalGespeeldeUitdagingenNormaal, aantalGespeeldeUitdagingenMoeilijk);
    }

    //KLASSEMENT
    /**
     * Geeft het klassement van de makkelijke moeilijkheidsgraad terug.
     *
     * @return een List (klassement) van String[] (spelersnaam + punten).
     */
    public List<String[]> geefKlassementMakkelijk() {
        return mapper.geefKlassementMakkelijk();
    }

    /**
     * Geeft het klassement van de normale moeilijkheidsgraad terug.
     *
     * @return een List (klassement) van String[] (spelersnaam + punten).
     */
    public List<String[]> geefKlassementNormaal() {
        return mapper.geefKlassementNormaal();
    }

    /**
     * Geeft het klassement van de moeilijke moeilijkheidsgraad terug.
     *
     * @return een List (klassement) van String[] (spelersnaam + punten).
     */
    public List<String[]> geefKlassementMoeilijk() {
        return mapper.geefKlassementMoeilijk();
    }

}
