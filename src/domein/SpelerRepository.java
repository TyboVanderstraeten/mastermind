package domein;

import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;

    //constructors
    public SpelerRepository() {
        mapper = new SpelerMapper();
    }

    //operaties
    public Speler geefSpeler(String spelersnaam, String wachtwoord) {
        Speler speler = mapper.geefSpeler(spelersnaam);
        if(speler.getWachtwoord().equals(wachtwoord)){
            return speler;
        }
        return null;
    }
    //aanpassingen in mapperklasse nodig voor geefSpeler(..) en voegSpelerToe(..)

    public void voegSpelerToe(Speler speler) {
        this.mapper.voegSpelerToe(speler);
    }
    
}
