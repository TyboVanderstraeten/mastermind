package domein;

import persistentie.SpelerMapper;

public class SpelerRepository {

    private SpelerMapper mapper;

    //constructors
    public SpelerRepository() {
        mapper = new SpelerMapper();
    }

    //operaties
    public Speler geefSpeler(String spelersnaam, String wachtwoord) {
        return mapper.geefSpeler();   //er moeten nog argumenten meegegeven worden aan geefSpeler()          
    }
    //aanpassingen in mapperklasse nodig voor geefSpeler(..) en voegSpelerToe(..)

    public void voegSpelerToe(Speler speler) {
        this.mapper.voegSpelerToe(speler);
    }
}
