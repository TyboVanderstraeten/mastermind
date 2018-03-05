package domein;

import java.security.SecureRandom;

public class Spel {

    private final Spelbord spelbord;
    private final String moeilijkheidsgraad;
    private final String willekeurigeCode;
    private boolean isGewonnen;
    private String spelnaam;

    public Spel(String moeilijkheidsgraad) {
        this(null, moeilijkheidsgraad, null, false); //aanpassing nodig voor random code generation        

    }

    public Spel(String spelnaam, String moeilijkheidsgraad, String willekeurigeCode, boolean isGewonnen) {
        spelbord = new Spelbord();
        setSpelnaam(spelnaam);
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        setIsGewonnen(isGewonnen);
        if (willekeurigeCode != null) {
            this.willekeurigeCode = willekeurigeCode;
        } else {                                            //genereert de random code via een random int (als index) die dan telkens in de kleurenArray een kleur kiest
            SecureRandom random = new SecureRandom();
            int guard = 0;
            String code = "";
            String[] kleuren = {"groen", "blauw", "rood", "paars", "geel", "rood", "oranje", "grijs"};      //willekeurige kleuren? of staat dit ergens??
            if (this.moeilijkheidsgraad.equals("gemakkelijk") || this.moeilijkheidsgraad.equals("normaal")) {   //this.moeilijkheidsgraad met dan eerst de controle in setter of gwn moeilijkheidsgraad?
                guard = 4;
            } else if (this.moeilijkheidsgraad.equals("moeilijk")) {
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

    public boolean getIsGewonnen() {
        return isGewonnen;
    }

    public String getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    private void setIsGewonnen(boolean isGewonnen) {
        this.isGewonnen = isGewonnen;
    }

    private void setSpelnaam(String spelnaam) {
        this.spelnaam = spelnaam;
    }

}
