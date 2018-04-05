package domein;

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
    public void registreerSpel(String spelnaam, String spelersnaam, Spel spel) {
        mapper.voegSpelToe(spelnaam, spelersnaam, spel);
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

    public void verwijderSpel(int spelnaam, String spelersnaam) {
        mapper.verwijderSpel(geefSpellen(spelersnaam)[spelnaam], spelersnaam);
    }
    
//    public Spel laadSpel(int spelnaam, String spelersnaam){
//        mapper.laadSpel(geefSpellen(spelersnaam)[spelnaam], spelersnaam);
//    }

}
