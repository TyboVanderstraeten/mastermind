package domein;

import java.util.Arrays;

public class Spelbord {

    private final Rij[] rijen;
    private final String[] willekeurigeCode;
    
    public Spelbord(int moeilijkheidsgraad, String[] willekeurigeCode) {
        rijen = new Rij[12];
        for(int i =0; i<12; i++){
            rijen[i]=new Rij();
        }
        
        this.willekeurigeCode = willekeurigeCode;
    }
    
    public String[][] geefOverzichtMetPinnen(){ 
        String[][] overzicht = new String[rijen.length][];
    for(int i = 0; i<rijen.length; i++){
           overzicht[i] = rijen[i].geefPinkleuren();
        }
        return overzicht;
    }    
    
}
