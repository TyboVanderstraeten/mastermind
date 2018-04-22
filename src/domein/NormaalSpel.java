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
        this(null);
    }
    
    public NormaalSpel(int[] willekeurigeCode) {
        this(willekeurigeCode, 0);
        
    }
    
    //voor uitdaging
    public NormaalSpel(int[] willekeurigeCode, int uitdagingNummer){
        super(willekeurigeCode);
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new NormaleRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }
    }

    @Override
    protected final int[] genereerWillekeurigeCode() {
        int[] willekeurigeCode = new int[4];
        //String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};        
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < willekeurigeCode.length; i++) {            
            willekeurigeCode[i] = random.nextInt(8);  
        }
        return willekeurigeCode;
    }

}
