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
     * @return
     */
    public Speler geefSpeler(String spelersnaam, String wachtwoord) {
        Speler speler = mapper.geefSpeler(spelersnaam);
        if (speler.getWachtwoord().equals(wachtwoord)) {
            return speler;
        }
        return null;
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

    public Speler kiesTegenspeler(String spelersnaam) {
        return mapper.geefSpeler(spelersnaam);
    }

    public void updateSpelerAantalGewonnen(String spelersnaam, int aantalGewonnenMakkelijk, int aantalGewonnenNormaal, int aantalGewonnenMoeilijk) {
        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalGewonnenMakkelijk, aantalGewonnenNormaal, aantalGewonnenMoeilijk);
    }
    
    public void updateAantalGewonnenUitdagingen(String spelersnaam, int aantalGewonnenuitdagingenMakkelijk, int aantalGewonnenuitdagingenNormaal, int aantalGewonnenuitdagingenMoeilijk) {
        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalGewonnenuitdagingenMakkelijk, aantalGewonnenuitdagingenNormaal, aantalGewonnenuitdagingenMoeilijk);
    }
    public void updateAantalPunten(String spelersnaam, int aantalPuntenMakkelijk, int aantalPuntenNormaal, int aantalPuntenMoeilijk) {
        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalPuntenMakkelijk, aantalPuntenNormaal, aantalPuntenMoeilijk);
    }
    public void updateAantalGespeeldeUitdagingen(String spelersnaam, int aantalGespeeldeUitdagingenMakkelijk, int aantalGespeeldeUitdagingenNormaal, int aantalGespeeldeUitdagingenMoeilijk) {
        mapper.updateSpelerAantalGewonnen(spelersnaam, aantalGespeeldeUitdagingenMakkelijk, aantalGespeeldeUitdagingenNormaal, aantalGespeeldeUitdagingenMoeilijk);
    }
    
    
    public String[] geefTegenspelers(String naamUitdagingenCategorie, int aantalGewonnenUitdagingen, String spelersnaam) {
        String[] tegenspelerNamen = new String[mapper.geefTegenspelers(naamUitdagingenCategorie, aantalGewonnenUitdagingen, spelersnaam
        ).size()];
        int teller = 0;

        for (String tegenspelerNaam : mapper.geefTegenspelers(naamUitdagingenCategorie, aantalGewonnenUitdagingen, spelersnaam)) {
            tegenspelerNamen[teller] = tegenspelerNaam;
            teller++;
        }

        return tegenspelerNamen;
    }
    
    //KLASSEMENT
    public List<String[]> geefKlassementMakkelijk(){
        return mapper.geefKlassementMakkelijk();    
    }
    
    public List<String[]> geefKlassementNormaal(){
        return mapper.geefKlassementNormaal();
    }
    
    public List<String[]> geefKlassementMoeilijk(){
        return mapper.geefKlassementMoeilijk();
    }

}
