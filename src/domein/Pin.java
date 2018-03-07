package domein;

public abstract class Pin {

    private String kleur = "X";

    public Pin(){
        
    }    

    public String getKleur() {
        return kleur;
    }

    private void setKleur(String kleur) {
        this.kleur = kleur;
    }
}
