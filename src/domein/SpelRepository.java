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
    public void registreerSpel(String spelnaam, String spelersnaam, Spel spel, String tegenspeler) {
        mapper.voegSpelToe(spelnaam, spelersnaam, spel, tegenspeler);
    }

    public String[] geefSpellen(String spelersnaam) {
        String[] spelnamen = new String[mapper.geefSpelnamen(spelersnaam).size()];
        int teller = 0;

        for (String spelnaam : mapper.geefSpelnamen(spelersnaam)) {
            spelnamen[teller] = spelnaam;
            teller++;
        }

        return spelnamen;
    }

    public Spel geefSpel(String spelersnaam, String spelnaam, int uitdaging) {
        return mapper.laadSpel(spelnaam, spelersnaam, uitdaging);
    }

    public void verwijderSpel(String spelnaam, String spelersnaam) {
        mapper.verwijderSpel(spelnaam, spelersnaam);
    }

    public void spelIsUitdaging(String spelnaam, String spelersnaam) {
        mapper.spelIsUitdaging(spelnaam, spelersnaam);
    }

   public String[][] geefUitdagingen(String spelersnaam) {
        
        List<String[]> uitdagingInfo = mapper.geefLijstUitdagingen(spelersnaam);
        String[][] uitdagingen = new String[uitdagingInfo.size()][];
        for(int i = 0; i<uitdagingen.length; i++){
            uitdagingen[i]= uitdagingInfo.get(i);
        }        
        return uitdagingen;
    }
 
//    public Spel laadSpel(int spelnaam, String spelersnaam){
//        mapper.laadSpel(geefSpellen(spelersnaam)[spelnaam], spelersnaam);
//    }
}
