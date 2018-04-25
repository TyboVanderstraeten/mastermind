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
        this(null, 0);        
    }
    
    public MoeilijkSpel(int[] willekeurigeCode) {
        this(willekeurigeCode, 0);
        
    }
    
    //voor uitdaging
    public MoeilijkSpel(int[] willekeurigeCode, int uitdagingNummer){
        super(willekeurigeCode, uitdagingNummer);
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MoeilijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }
        
    }

    @Override
    protected final int[] genereerWillekeurigeCode() {
        //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs", "X"};
        //int[] kleuren2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] willekeurigeCode = new int[5];
        SecureRandom random = new SecureRandom();
        int keuze = 9;
        int aantal = 0;
        for (int i = 0; i < 5; i++) {
            int getal = random.nextInt(keuze);
            willekeurigeCode[i] = getal;
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
