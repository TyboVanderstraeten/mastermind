package domein;

import java.util.List;
import persistentie.SpelMapper;

public class SpelRepository {

    private final SpelMapper mapper;

    public SpelRepository() {
        mapper = new SpelMapper();
    }

    public String[][] startMasterMind() {
        List<Spel> spellen = mapper.geefSpellen();
        String[][] overzicht = new String[2][];
        
        
        return null;

    }

}
