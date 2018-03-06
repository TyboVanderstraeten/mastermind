package domein;

public class Spelbord {

    private final Rij[] rijen;
    
    public Spelbord() {
        rijen = new Rij[12];
        for(int i =0; i<12; i++){
            rijen[i]=new Rij();
        }
    }
    
    public String[][] geefOverzichtMetPinnen(){ 
        String[][] overzicht = new String[rijen.length][];
    for(int i = 0; i<rijen.length; i++){
           overzicht[i] = rijen[i].geefPinkleuren();
        }
        return overzicht;
    }
}
