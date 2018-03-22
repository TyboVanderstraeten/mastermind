package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad moeilijk is.
 *
 */
public class MoeilijkSpel extends Spel {

    /**
     * Class constructor. Roept de default constructor van de superklasse Spel
     * aan.
     *
     * @see Spel
     */
    public MoeilijkSpel() {
        super();
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MoeilijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }

        //Denk niet dat klopt, werkt wel maar vrij omslachtig
    }
    
    public MoeilijkSpel(String spelnaam, String spelersNaam, String willekeurigeCode) {
        super();
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MoeilijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }

        //Denk niet dat klopt, werkt wel maar vrij omslachtig
    }

    @Override
    protected final String[] genereerWillekeurigeCode() {
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "X"};
        String[] willekeurigeCode = new String[5];
        SecureRandom random = new SecureRandom();
        int keuze = 9;
        int aantal = 0;
        for (int i = 0; i < 5; i++) {
            int getal = random.nextInt(keuze);
            willekeurigeCode[i] = kleuren[getal];
            if (getal == 8) {
                aantal++;
                if (aantal == 2) {
                    keuze = 8;
                }
            }
        }
        return willekeurigeCode;
    }

}
