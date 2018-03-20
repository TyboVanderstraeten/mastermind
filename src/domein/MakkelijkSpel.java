package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad makkelijk is.
 *
 */
public class MakkelijkSpel extends Spel {

    /**
     * Class constructor. roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public MakkelijkSpel() {
        super();
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MakkelijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }

    }

    @Override
    protected final String[] genereerWillekeurigeCode() {
        String[] willekeurigeCode = new String[4];
        //RANDOMCODE GENERATOR        
        SecureRandom random = new SecureRandom();
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??

        int keuze = 8;
        for (int i = 0; i < willekeurigeCode.length; i++) {
            int getal = random.nextInt(keuze);
            willekeurigeCode[i] = kleuren[getal];
            kleuren[getal] = kleuren[keuze - 1];
            keuze--;
        }
        return willekeurigeCode;
    }
}
