package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad makkelijk is.
 *
 */
public class MakkelijkSpel extends Spel {

    private final String[] willekeurigeCode = new String[4];

    /**
     * Class constructor. roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public MakkelijkSpel() {
        super();
    }

    @Override
    protected void genereerWillekeurigeCode() {
        //RANDOMCODE GENERATOR        
        SecureRandom random = new SecureRandom();
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??

        int keuze = 8;
        for (int i = 0; i < willekeurigeCode.length; i++) {
            int getal = random.nextInt(keuze);
            willekeurigeCode[i] = kleuren[getal];
            kleuren[getal] = kleuren[keuze - 1];
            keuze--;
        }
        
    }
}
