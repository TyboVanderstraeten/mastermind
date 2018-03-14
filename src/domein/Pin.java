package domein;

/**
 * Abstracte superklasse van Codepin en Evaluatiepin
 *
 */
public abstract class Pin {

    private final String kleur;

    /**
     * Class constructor geeft het attribuut kleur de waarde van de parameter.
     *
     * @param kleur de kleur van de pin.
     */
    public Pin(String kleur) {
        this.kleur = kleur;
    }

    /**
     * Getter. geeft het attribuut kleur terug.
     *
     * @return
     */
    public String getKleur() {
        return kleur;
    }
    
    
}
