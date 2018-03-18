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

}
