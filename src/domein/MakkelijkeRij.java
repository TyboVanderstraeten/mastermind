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

    @Override
    public void geefPoging(int[] poging, int[] willekeurigeCode) {
        int[] code = new int[willekeurigeCode.length];
        System.arraycopy( willekeurigeCode, 0, code, 0, willekeurigeCode.length );
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (code[i] == poging[i]) {
                getEvaluatiepinnen()[i] = new EvaluatiePin(9);          //ZWART         
                code[i] = -111;
            }
        }
        for (int i = 0; i < getCodepinnen().length; i++) {            
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {
                if (code[j] == poging[i]) {
                    getEvaluatiepinnen()[i] = new EvaluatiePin(8);         //WIT
                }
            }
        }
    }
}
