package domein;

import java.util.List;
import persistentie.SpelMapper;

public class SpelRepository {

    private final SpelMapper mapper;

    public SpelRepository() {
        mapper = new SpelMapper();
    }
    
   /** public Spel[] geefSpellen(){
        List<Spel> spellen =mapper.geefSpellen();
        Spel[] spelletjes = new Spel[spellen.size()];
        for(int i =0; i<spelletjes.length; i++){
            spelletjes[i]=spellen.get(i);
        }
        return spelletjes;
    }
**/
    

}
