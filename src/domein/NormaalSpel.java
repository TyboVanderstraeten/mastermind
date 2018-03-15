package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad normaal is.
 *
 */
public class NormaalSpel extends Spel {
    private final String[] willekeurigeCode = new String[4];
    /**
     * Class constructor roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public NormaalSpel() {
        super();
    }

    @Override
    protected void genereerWillekeurigeCode() {
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs"};
        SecureRandom random = new SecureRandom();
            for (int i = 0; i < willekeurigeCode.length; i++) {
                int getal = random.nextInt(8);
                willekeurigeCode[i] = kleuren[getal];
            }
    }
    
    

}
