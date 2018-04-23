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
        this(null);
    }
    
    public MakkelijkSpel(int[] willekeurigeCode) {
           this(willekeurigeCode, 0);
    }
    
    //voor uitdaging
    public MakkelijkSpel(int[] willekeurigeCode, int uitdagingNummer){
        super(willekeurigeCode, uitdagingNummer);                        
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MakkelijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }     
    }

    @Override
    protected final int[] genereerWillekeurigeCode() {
        int[] willekeurigeCode = new int[4];
        //RANDOMCODE GENERATOR        
        SecureRandom random = new SecureRandom();
//        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??
        int[] kleuren = {0,1, 2, 3, 4, 5, 6, 7};

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




//@Override
//    protected final String[] genereerWillekeurigeCode() {
//        String[] willekeurigeCode = new String[4];
//        //RANDOMCODE GENERATOR        
//        SecureRandom random = new SecureRandom();
//        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??
//        //int[] kleuren2 = {0,1, 2, 3, 4, 5, 6, 7};
//
//        int keuze = 8;
//        for (int i = 0; i < willekeurigeCode.length; i++) {
//            int getal = random.nextInt(keuze);
//            willekeurigeCode[i] = kleuren[getal];
//            kleuren[getal] = kleuren[keuze - 1];
//            keuze--;
//        }
//        return willekeurigeCode;
//    }
