package domein;

/**
 * Subklasse van Pin die alleen de codepinnen omvat.
 *
 */
public class CodePin extends Pin {

    /**
     * Class constructor met String als parameter. Roept de Constructor met
     * String als parameter bij de superklasse Pin aan.
     *
     * @param kleur de kleur van de pin.
     * @see Pin
     */
    public CodePin(String kleur) {
        super(kleur);
    }    
}
