package domein;

import java.util.Arrays;

/**
 * Het Spelbord. Bevat de attributen rijen en willekeurigeCode *
 */
public class Spelbord {

    private final Rij[] rijen;
    private final int[] willekeurigeCode;// = {0,1,0,0,2};
    private int aantalPogingen;

    /**
     * Class constructor met een String[] als parameter. Geeft het attribuut
     * willekeurigeCode de waarde van de parameter. vult de array rijen met Rij
     * objecten afhankelijk van de moeilijkheidsgraad.
     *
     * @param willekeurigeCode random gegenereerde code die speler moet proberen
     * bekomen.
     */
    public Spelbord(int[] willekeurigeCode) {
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
     * @return een int[][] die de pinwaarden per rij bevat.
     */
    public int[][] geefOverzichtMetPinnen() {
        int[][] overzicht = new int[rijen.length][];
        for (int i = 0; i < rijen.length; i++) {
            overzicht[i] = rijen[i].geefPinkleuren();
        }

        int[] huidig = Arrays.copyOfRange(overzicht[aantalPogingen == 0 ? 0 : aantalPogingen - 1], 0, willekeurigeCode.length);
        for (int i = 0; i < rijen[rijen.length - 1].getCodepinnen().length; i++) {
            overzicht[rijen.length - 1][rijen[rijen.length - 1].getCodepinnen().length + 1 + i] = -2;           //-2 = ""    in deze lijn wordt de evaluatie weggedaan bij de laatste rij (te raden code).   +1 omdat Evaluatie: ook een plek heeft.
            if (!Arrays.toString(huidig).replaceAll("\\s", "").equals(Arrays.toString(willekeurigeCode).replaceAll("\\s", "")) && aantalPogingen != 12) {
                overzicht[rijen.length - 1][i] = -3;      //-3 = #                           //INDIEN DE CODE NIET GEKRAAKT IS, IS DEZE GEMASKEERD (#)
            }

        }
        return overzicht;
    }

    /**
     * verhoogt het aantal pogingen en geeft de poging door aan de juiste rij.
     *
     * @param poging kleuren combinatie die de speler ingeeft.
     */
    public void geefPoging(int[] poging) {
        rijen[aantalPogingen].geefPoging(poging, willekeurigeCode);
        aantalPogingen++;

    }

    //GETTERS
    /**
     * Getter.
     * Geeft het attribuut willekeurigeCode terug.
     * 
     * @return de te kraken code.
     */
    public int[] getWillekeurigeCode() {
        return willekeurigeCode;
    }

    /**
     * Getter.
     * Geeft het attribuut aantal pogingen terug.
     * 
     * @return het aantal pogingen.
     */
    public int getAantalPogingen() {
        return aantalPogingen;
    }

    /**
     * Getter.
     * Geeft een array met de rijen terug.
     * 
     * @return een array met rij Objecten (verschillend per moeilijkheidsgraad).
     */
    public Rij[] getRijen() {
        return rijen;
    }

}
