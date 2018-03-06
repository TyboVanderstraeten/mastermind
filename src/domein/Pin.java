package domein;

public abstract class Pin {

    private String kleur;

    public Pin(){
        this("X");
    }
    public Pin(String kleur) {
        setKleur(kleur);
    }    

    public String getKleur() {
        return kleur;
    }

    private void setKleur(String kleur) {
        this.kleur = kleur;
    }
}
