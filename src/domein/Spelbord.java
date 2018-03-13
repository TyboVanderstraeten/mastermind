package domein;
/**
 * Het Spelbord.
 * Bevat de attributen rijen en willekeurigeCode * 
 */
public class Spelbord {

    private final Rij[] rijen;
    private final String[] willekeurigeCode;

    /**
     * Class constructor met een String[] als parameter.
     * Geeft het attribuut willekeurigeCode de waarde van de parameter.
     * vult de array rijen met Rij objecten afhankelijk van de moeilijkheidsgraad.
     * 
     * @param willekeurigeCode random gegenereerde code die speler moet proberen bekomen. 
     */
    public Spelbord(String[] willekeurigeCode) {
        this.willekeurigeCode = willekeurigeCode;
        rijen = new Rij[13];

        for (int i = 0; i < 13; i++) {
            if (willekeurigeCode.length == 4) {
                rijen[i] = new MakkelijkeNormaleRij();
            } else {
                rijen[i] = new MoeilijkeRij();
            }
        }        
        
        for (int i = 0; i < willekeurigeCode.length; i++) {
            rijen[rijen.length-1].getCodepinnen()[i] = new CodePin(willekeurigeCode[i]);
        }
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
        return overzicht;
    }

}
