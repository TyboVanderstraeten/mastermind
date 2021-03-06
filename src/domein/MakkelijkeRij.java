package domein;

/**
 * Subklasse van Rij waarbij de moeilijkheidsgraad makkelijk of normaal is.
 *
 */
public class MakkelijkeRij extends Rij {

    /**
     * Class constructor. Roept de default constructor van de superklasse Rij
     * aan.
     *
     * @see Rij
     */
    public MakkelijkeRij() {
        super(new CodePin[4]);
    }

    /**
     * Zorgt ervoor dat de codepinnen en evaluatiepinnen de juiste kleuren krijgen.
     * 
     * @param poging de poging die de speler heeft ingegeven.
     * @param willekeurigeCode de te kraken code.
     */
    @Override
    public void geefPoging(int[] poging, int[] willekeurigeCode) {
        int[] code = new int[willekeurigeCode.length];
        System.arraycopy( willekeurigeCode, 0, code, 0, willekeurigeCode.length );
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (code[i] == poging[i]) {
                getEvaluatiepinnen()[i] = new EvaluatiePin(9);          //ZWART         
                code[i] = -111; //zodat hier niet meer opgechecked wordt
            }
        }
        for (int i = 0; i < getCodepinnen().length; i++) {            
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {
                if (code[j] == poging[i]) {
                    getEvaluatiepinnen()[i] = new EvaluatiePin(10);         //WIT
                }
            }
        }
    }
}
