package domein;

public class Spelbord {

    private final Rij[] rijen;
    private final String[] willekeurigeCode;

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
    }

    public String[][] geefOverzichtMetPinnen() {
        String[][] overzicht = new String[rijen.length][];
        for (int i = 0; i < rijen.length; i++) {
            overzicht[i] = rijen[i].geefPinkleuren();
        }
        return overzicht;
    }

}
