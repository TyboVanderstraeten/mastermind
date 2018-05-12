package domein;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad makkelijk is.
 *
 */
public class MakkelijkSpel extends Spel {

    /**
     * Class constructor.
     * Wordt aangeroepen als er een nieuw spel wordt aangemaakt. 
     * De constructor zal een default waarde meegeven om de constructor
     * met willekeurigeCode en uitdagingNummer aan te roepen.
     *
     * @see Spel
     */
    public MakkelijkSpel() {
        this(null, 0);
    }
    
    /** 
     * Class constructor. 
     * Geeft een default waarde aan uitdagingNummer om constructor met uitdagingNummer aan te roepen.
     * 
     * @param willekeurigeCode De code die gekraakt moet worden.
     */
    public MakkelijkSpel(int[] willekeurigeCode) {
           this(willekeurigeCode, 0);
    }
    
    //voor uitdaging
    /**
     * Class constructor.
     * Zal de constructor van spel aanroepen en de correcte rijen genereren aan de hand van de moeilijkheidsgraad (hier makkelijk).
     * 
     * @param willekeurigeCode de code die gekraakt moet worden.
     * @param uitdagingNummer het id van de uitdaging. (0 indien het geen uitdaging is)
     */
    public MakkelijkSpel(int[] willekeurigeCode, int uitdagingNummer){
        super(willekeurigeCode, uitdagingNummer);                        
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new MakkelijkeRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }     
    }

    
    /**
     * Methode die de willekeurige code voor de makkelijke moeilijkheidsgraad zal genereren.
     * 
     * @return een int[] die de te kraken code bevat.
     */
    @Override
    protected final int[] genereerWillekeurigeCode() {
        int[] willekeurigeCode = new int[4];
        //RANDOMCODE GENERATOR        
        SecureRandom random = new SecureRandom();
//        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "bruin", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??
        int[] kleuren = {0, 1, 2, 3, 4, 5, 6, 7};

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
