package domein;

import java.util.Arrays;

/**
 * Het Spelbord. Bevat de attributen rijen en willekeurigeCode *
 */
public class Spelbord {

    private final Rij[] rijen;
    private final String[] willekeurigeCode;
    private int aantalPogingen;
    private boolean isGewonnen;

    /**
     * Class constructor met een String[] als parameter. Geeft het attribuut
     * willekeurigeCode de waarde van de parameter. vult de array rijen met Rij
     * objecten afhankelijk van de moeilijkheidsgraad.
     *
     * @param willekeurigeCode random gegenereerde code die speler moet proberen
     * bekomen.
     */
    public Spelbord(String[] willekeurigeCode) {
        this.willekeurigeCode = willekeurigeCode;
        aantalPogingen = 0;
        rijen = new Rij[13];
//        for (int i = 0; i < 13; i++) {
//            if ("MakkelijkSpel".equals(Spel.class.getSimpleName())) {
//                rijen[i] = new MakkelijkeRij();
//            } else {
//                rijen[i] = new MoeilijkeRij();
//            }
//        }

//        for (int i = 0; i < willekeurigeCode.length; i++) {
//            rijen[rijen.length - 1].getCodepinnen()[i] = new CodePin(willekeurigeCode[i]);
//        }
    }

    /**
     * Roept de methode geefPinkleuren aan voor elke Rij en geeft een
     * multidimensionele array terug die de kleuren per rij bevat.
     *
     * @return
     */
    public String[][] geefOverzichtMetPinnen() {        
        String[][] overzicht = new String[rijen.length][];
        for (int i = 0; i < rijen.length; i++) {
            overzicht[i] = rijen[i].geefPinkleuren();            
        }
        if(overzicht[aantalPogingen] != willekeurigeCode || aantalPogingen != 12){
            overzicht[rijen.length-1][rijen[rijen.length-1].getCodepinnen().length] = "";
            for(int i = 0; i<rijen[rijen.length-1].getCodepinnen().length; i++){
                overzicht[rijen.length-1][i] = String.format("%-6s", "#");
                overzicht[rijen.length-1][rijen[rijen.length-1].getCodepinnen().length+1+i] = String.format("%-5s", " ");
            }
                
            }
        return overzicht;
    }

    public void geefPoging(String[] poging) {
        rijen[aantalPogingen].geefPoging(poging, willekeurigeCode);
        aantalPogingen++;
        if (Arrays.equals(poging, willekeurigeCode)) {
            this.isGewonnen = true;
        }
    }

    //GETTERS
    public String[] getWillekeurigeCode() {
        return willekeurigeCode;
    }

    public int getAantalPogingen() {
        return aantalPogingen;
    }

    public Rij[] getRijen() {
        return rijen;
    }
    
    

}
