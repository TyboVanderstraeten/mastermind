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
    public void geefPoging(String[] poging, String[] willekeurigeCode) {
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (willekeurigeCode[i].equals(poging[i])) {
                getEvaluatiepinnen()[i] = new EvaluatiePin("Zwart");
                continue;
            }
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {
                if (willekeurigeCode[j].equals(poging[i])) {
                    getEvaluatiepinnen()[i] = new EvaluatiePin("Wit");
                }
            }
        }
    }
}
