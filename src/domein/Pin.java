package domein;

public class Pin {

    private String kleur;

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
