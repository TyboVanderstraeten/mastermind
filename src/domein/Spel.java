package domein;

import java.security.SecureRandom;

public abstract class Spel {

    private final Spelbord spelbord;    
    private final String willekeurigeCode;    
    private String spelnaam;

    
    public Spel(int moeilijkheidsgraad){
        this(null, null);
    }
    public Spel(String spelnaam, String willekeurigeCode) {
        spelbord = new Spelbord();
        setSpelnaam(spelnaam);
         
        if (willekeurigeCode != null) {
            this.willekeurigeCode = willekeurigeCode;
        } else {                                            //genereert de random code via een random int (als index) die dan telkens in de kleurenArray een kleur kiest
            SecureRandom random = new SecureRandom();
            int guard = 0;
            String code = "";
            
            String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??
            if (this.getClass().getSimpleName().equals("MakkelijkSpel") || this.getClass().getSimpleName().equals("NormaalSpel")) {   //this.moeilijkheidsgraad met dan eerst de controle in setter of gwn moeilijkheidsgraad?
                guard = 4;
            } else if (this.getClass().getSimpleName().equals("MoeilijkSpel")) {
                guard = 5;
            }
            for (int i = 0; i < guard; i++) {                               //NOG AANPASSING NODIG IN VERBAND MET MOEILIJKHEIDSGRAAD (UNIEKE KLEUREN OF NIET / LEEG OF NIET)
                int getal = random.nextInt(8);
                code += String.format("%s ", kleuren[getal]);

            }
            this.willekeurigeCode = code;
        }
    }

    public Spelbord getSpelbord() {
        return spelbord;
    }
        

    private void setSpelnaam(String spelnaam) {
        this.spelnaam = spelnaam;
    }

}


//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.