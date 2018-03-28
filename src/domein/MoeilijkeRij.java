package domein;

import java.util.Arrays;

/**
 * Subklasse van Rij waarbij de moeilijkheidsgraad moeilijk is.
 *
 */
public class MoeilijkeRij extends Rij {

    /**
     * Class constructor. Roept de default constructor van de klasse Rij aan.
     *
     * @see Rij
     */
    public MoeilijkeRij() {
        super(new CodePin[5]);
    }

    @Override
    public void geefPoging(int[] poging, int[] willekeurigeCode) {
        int zwart = 0;
        int wit = 0;

        int[] aantal = new int[8];
        for (int j = 0; j < poging.length; j++) {
            aantal[willekeurigeCode[j]]++;
        }
        
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (willekeurigeCode[i] == poging[i]) {
                zwart++;
                aantal[poging[i]]--;
                if (aantal[poging[i]] < 0 && wit > 0) {
                    wit--;
                }
                continue;
            }
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {

                if (willekeurigeCode[j] == poging[i]) {
                    --aantal[poging[i]];
                    if (aantal[poging[i]] < 0) {
                        wit--;
                    }        
                    wit++;
                    break;
                }

            }
        }

        for (int i = 0; i < zwart; i++) {
            getEvaluatiepinnen()[i] = new EvaluatiePin(9);  //ZWART
        }
        for (int i = zwart; i < zwart + wit; i++) {
            getEvaluatiepinnen()[i] = new EvaluatiePin(8); //WIT
        }

    }

}
