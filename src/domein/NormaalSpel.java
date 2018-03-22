package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad normaal is.
 *
 */
public class NormaalSpel extends Spel {

    /**
     * Class constructor roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public NormaalSpel() {
        super();
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new NormaleRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }

        //Denk niet dat klopt, werkt wel maar vrij omslachtig
    }
    
    public NormaalSpel(String spelnaam, String spelersNaam, String willekeurigeCode) {
        super();
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new NormaleRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }
        
        //Denk niet dat klopt, werkt wel maar vrij omslachtig
    }

    @Override
    protected final String[] genereerWillekeurigeCode() {
        String[] willekeurigeCode = new String[4];
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};
        //int[] kleuren2 = {0,1, 2, 3, 4, 5, 6, 7};
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < willekeurigeCode.length; i++) {
            int getal = random.nextInt(8);
            willekeurigeCode[i] = kleuren[getal];
        }
        return willekeurigeCode;
    }

}
