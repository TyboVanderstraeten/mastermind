package domein;

public abstract class Pin {

    private final String kleur;

    public Pin(String kleur){
        this.kleur = kleur;
    }    

    public String getKleur() {
        return kleur;
    }    
    
}
