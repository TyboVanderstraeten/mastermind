package domein;

import java.security.SecureRandom;

/**
 * Subklasse van Spel waarbij de moeilijkheidsgraad normaal is.
 *
 */
public class NormaalSpel extends Spel {

    /**
     * Class constructor. 
     * Wordt aangeroepen als er een nieuw spel wordt aangemaakt voor de normale moeilijkheidsgraad. 
     * De constructor zal een default waarde meegeven om de constructor
     * met willekeurigeCode en uitdagingNummer aan te roepen.
     *
     * @see Spel
     */
    public NormaalSpel() {
        this(null, 0);
    }
    /**
     * Class constructor.
     * Zal default waarde meegeven om de constructor met willekeurigeCode en uitdagingNummer aan te roepen.
     * 
     * @param willekeurigeCode de te kraken code.
     */
    public NormaalSpel(int[] willekeurigeCode) {
        this(willekeurigeCode, 0);
        
    }
    
    //voor uitdaging
    /**
     * Class constructor.
     * Zal de constructor van spel aanroepen en de correcte rijen genereren aan de hand van de moeilijkheidsgraad (hier normaal).
     * @param willekeurigeCode
     * @param uitdagingNummer 
     */
    public NormaalSpel(int[] willekeurigeCode, int uitdagingNummer){
        super(willekeurigeCode, uitdagingNummer);
        for (int i = 0; i < super.getSpelbord().getRijen().length; i++) {
            super.getSpelbord().getRijen()[i] = new NormaleRij();
        }
        for (int i = 0; i < super.getSpelbord().getWillekeurigeCode().length; i++) {
            super.getSpelbord().getRijen()[super.getSpelbord().getRijen().length - 1].getCodepinnen()[i] = new CodePin(super.getSpelbord().getWillekeurigeCode()[i]);
        }
    }

    /**
     * Deze methode zal de willekeurige code genereren voor de normale moeilijkheidsgraad.
     * 
     * @return int[] die de te kraken code bevat.
     */
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
