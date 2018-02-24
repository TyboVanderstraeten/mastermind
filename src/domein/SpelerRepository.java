
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelerMapper;


public class SpelerRepository {
    private List<Speler> spelerslijst;   //spelerslijst moet uiteindelijk in SpelerMapper worden opgeslagen, List zal dan verdwijnen
    //private SpelerMapper mapper;

    public SpelerRepository() {
        //mapper = new SpelerMapper();      
        spelerslijst = new ArrayList<>();
    }
    
    

    public String voegSpelerToe(Speler speler) {
        this.spelerslijst.add(speler);
        return speler.getSpelersnaam();
    }
}
