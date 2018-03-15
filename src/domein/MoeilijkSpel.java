package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad moeilijk is.
 *
 */
public class MoeilijkSpel extends Spel {
    private final String[] willekeurigeCode = new String[5];
    
    /**
     * Class constructor. Roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public MoeilijkSpel() {
        super();
        Spelbord spelbord = new Spelbord(willekeurigeCode);
    }

    @Override
    protected void genereerWillekeurigeCode() {
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs", "X"};
        SecureRandom random = new SecureRandom();
        int keuze = 9;
            int aantal = 0;            
            for (int i = 0; i < 5; i++) {
                int getal = random.nextInt(keuze);
                willekeurigeCode[i] = kleuren[getal];
                if (getal == 9) {
                    aantal++;
                    if (aantal == 2) {
                        keuze = 8;
                    }
                }
            }
    }

}
