/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author bramd
 */
public class NormaleRij extends Rij {

    public NormaleRij() {
        super(new CodePin[4]);
    }

    @Override
    public void geefPoging(String[] poging, String[] willekeurigeCode) {
        int zwart = 0;
        int wit = 0;
        for (int i = 0; i < getCodepinnen().length; i++) {
            getCodepinnen()[i] = new CodePin(poging[i]);
            if (willekeurigeCode[i].equals(poging[i])) {
                zwart++;
                continue;
            }
            for (int j = 0; j < getEvaluatiepinnen().length; j++) {
                if (willekeurigeCode[j].equals(poging[i])) {
                    wit++;
                }
            }
        }

        for (int i = 0; i < zwart; i++) {
            getEvaluatiepinnen()[i] = new EvaluatiePin("zwart");
        }
        for (int i = zwart; i < zwart + wit; i++) {
            getEvaluatiepinnen()[i] = new EvaluatiePin("Wit");
        }

    }
}
