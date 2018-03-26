package domein;

/**
 * Abstracte superklasse van Codepin en Evaluatiepin
 *
 */
public abstract class Pin {

    private final int kleur;
    
    /**
     * Class constructor geeft het attribuut kleur de waarde van de parameter.
     *
     * @param kleur de kleur van de pin.
     */
    public Pin(int kleur) {                          // IDEA VR LATER  ALT + 0149   •    en private static final ANSI_KLEUR
        this.kleur = kleur;        
    }

    /**
     * Getter. geeft het attribuut kleur terug.
     *
     * @return
     */
    public int getKleur() {
        return kleur;
    }

    
}
