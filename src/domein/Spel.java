package domein;

import java.security.SecureRandom;

public abstract class Spel {

    private final Spelbord spelbord;
    private final String[] willekeurigeCode;        //moet spel dit kennen? spelbord kent dit ook
    private String spelnaam;
    private int aantalPogingen;
    private String[] poging;

    public Spel() {
        //genereert de random code via een random int (als index) die dan telkens in de kleurenArray een kleur kiest
        //RANDOMCODE GENERATOR
        SecureRandom random = new SecureRandom();
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs", "X"};      //willekeurige kleuren? of staat dit ergens??
        if (this.getClass().getSimpleName().equals("MakkelijkSpel")) {
            int keuze = 8;
            willekeurigeCode = new String[4];
            for (int i = 0; i < willekeurigeCode.length; i++) {
                int getal = random.nextInt(keuze);
                willekeurigeCode[i] = kleuren[getal];
                kleuren[getal] = kleuren[keuze - 1];
                keuze--;
            }
        } else if (this.getClass().getSimpleName().equals("NormaalSpel")) {
            willekeurigeCode = new String[4];
            for (int i = 0; i < willekeurigeCode.length; i++) {
                int getal = random.nextInt(8);
                willekeurigeCode[i] = kleuren[getal];
            }
        } else {                                                             //else if (this.getClass().getSimpleName().equals("MoeilijkSpel")) 
            int keuze = 9;
            int aantal = 0;
            willekeurigeCode = new String[5];
            for (int i = 0; i < 5; i++) {
                int getal = random.nextInt(keuze);
                willekeurigeCode[i] = kleuren[getal];
                if (getal == 9) {
                    aantal++;
                    if (aantal == 2) {
                        keuze = 8;
                    }
                }
            }
        }
        spelbord = new Spelbord(willekeurigeCode);
    }

    
    public Spelbord getSpelbord() {
        return spelbord;
    }    

    //private void setSpelnaam(String spelnaam) {
    //    this.spelnaam = spelnaam;                 //nog niet nodig
    //}
    public void setPoging(String[] poging) {
        this.poging = poging;
    }
}

//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.
