package domein;

import java.util.Arrays;
import java.util.List;
import persistentie.SpelMapper;

/**
 * Zorgt voor de verbinding met de SpelMapper uit de persistentielaag.
 *
 */
public class SpelRepository {

    private final SpelMapper mapper;

    /**
     * Class constructor. roept de constructor van Spelmapper aan.
     */
    public SpelRepository() {
        mapper = new SpelMapper();
    }

//    public Spel[] geefSpellen(){
//        List<Spel> spellen =mapper.geefSpellen();
//        Spel[] spelletjes = new Spel[spellen.size()];
//        for(int i =0; i<spelletjes.length; i++){
//            spelletjes[i]=spellen.get(i);
//        }
//        return spelletjes;
//    }
    
    /**
     * Zorgt ervoor dat de mapper het spel met de juiste spelnaam zal registreren voor de speler met de juiste spelersnaam.
     * 
     * @param spelnaam de naam van het spel.
     * @param spelersnaam de naam van de huidige speler.
     * @param spel heb spel object.
     */
    public void registreerSpel(String spelnaam, String spelersnaam, Spel spel) {
        mapper.voegSpelToe(spelnaam, spelersnaam, spel);
    }

    /**
     * Zorgt ervoor dat de mapper alle opgeslagen spellen zal teruggeven van de huidige speler.
     * 
     * @param spelersnaam de naam van de huidige speler.
     * @return een String[][] met de spelnamen en moeilijkheidsgraden
     */
    public String[][] geefSpellen(String spelersnaam) {
        List<String[]> spelInfo = mapper.geefSpelnamen(spelersnaam);
        String[][] spellen = new String[spelInfo.size()][];
        for (int i = 0; i < spellen.length; i++) {
            spellen[i] = spelInfo.get(i);
        }
        return spellen;
    }

    /**
     * Zorgt ervoor dat de mapper het spel van de huidige speler met de meegegeven spelnaam geladen wordt.
     * 
     * @param spelersnaam de naam van de huidige speler.
     * @param spelnaam de naam van het spel.
     * @return het SpelObject van het opgevraagde spel.
     */
    public Spel geefSpel(String spelersnaam, String spelnaam) {
        return mapper.laadSpel(spelnaam, spelersnaam);
    }

    /**
     * Zorgt ervoor dat de mapper het spel van de meegegeven speler met de juiste spelnaam verwijdert wordt.
     * 
     * @param spelnaam de naam van het te verwijderen spel.
     * @param spelersnaam de naam van de speler.
     */
    public void verwijderSpel(String spelnaam, String spelersnaam) {
        mapper.verwijderSpel(spelnaam, spelersnaam);
    }

    //    public void spelIsUitdaging(String spelnaam, String spelersnaam) {
    //        mapper.spelIsUitdaging(spelnaam, spelersnaam);
    //    }
    //   public String[][] geefUitdagingen(String spelersnaam) {
    //        
    //        List<String[]> uitdagingInfo = mapper.geefLijstUitdagingen(spelersnaam);
    //        String[][] uitdagingen = new String[uitdagingInfo.size()][];
    //        for(int i = 0; i<uitdagingen.length; i++){
    //            uitdagingen[i]= uitdagingInfo.get(i);
    //        }        
    //        return uitdagingen;
    //    }
//    public Spel laadSpel(int spelnaam, String spelersnaam){
//        mapper.laadSpel(geefSpellen(spelersnaam)[spelnaam], spelersnaam);
//    }
}
