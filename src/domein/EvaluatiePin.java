package domein;

/**
 * Subklasse van Pin die alleen de evaluatiepinnen omvat.
 * 
 */
public class EvaluatiePin extends Pin{    
    
    /**
     * Class constructor met een String als parameter.
     * roept de constructor met een String als parameter van de superklasse Pin aan.
     * 
     * @param kleur 
     * @see Pin
     */
    public EvaluatiePin(String kleur){
        super(kleur);
    }
}
