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
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (willekeurigeCode[i] == poging[i]) {
                getEvaluatiepinnen()[i] = new EvaluatiePin(9);          //ZWART
                continue;
            }
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {
                if (willekeurigeCode[j] == poging[i]) {
                    getEvaluatiepinnen()[i] = new EvaluatiePin(8);         //WIT
                }
            }
        }
    }
}
