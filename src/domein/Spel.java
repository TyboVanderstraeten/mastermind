package domein;

import java.security.SecureRandom;

public abstract class Spel {

    private final Spelbord spelbord;
    private final String willekeurigeCode;
    private String spelnaam;

    public Spel(int moeilijkheidsgraad) {
        spelbord = new Spelbord(moeilijkheidsgraad);

        //genereert de random code via een random int (als index) die dan telkens in de kleurenArray een kleur kiest
        //RANDOMCODE GENERATOR
        SecureRandom random = new SecureRandom();
        String code = "";
        String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs", "X"};      //willekeurige kleuren? of staat dit ergens??
        if (this.getClass().getSimpleName().equals("MakkelijkSpel")) {
            int keuze = 8;
            for (int i = 0; i < 4; i++) {
                int getal = random.nextInt(keuze);
                code += String.format("%s ", kleuren[getal]);
                kleuren[getal] = kleuren[keuze - 1];
                keuze--;
            }
        } else if (this.getClass().getSimpleName().equals("NormaalSpel")) {
            for (int i = 0; i < 4; i++) {
                int getal = random.nextInt(8);
                code += String.format("%s ", kleuren[getal]);
            }
        } else if (this.getClass().getSimpleName().equals("MoeilijkSpel")) {
            int keuze = 9;
            int aantal = 0;
            for (int i = 0; i < 5; i++) {
                int getal = random.nextInt(keuze);
                code += String.format("%s ", kleuren[getal]);
                if (getal == 9) {
                    aantal++;
                    if (aantal == 2) {
                        keuze = 8;
                    }
                }
            }
        }
        this.willekeurigeCode = code;
    }

    public Spelbord getSpelbord() {
        return spelbord;
    }

    //private void setSpelnaam(String spelnaam) {
    //    this.spelnaam = spelnaam;                 //nog niet nodig
    //}

}

//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.
