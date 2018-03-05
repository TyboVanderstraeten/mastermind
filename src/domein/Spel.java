package domein;

public class Spel {

    private final Spelbord spelbord;
    private String moeilijkheidsgraad;
    private String willekeurigeCode;
    private boolean isGewonnen;

    public Spel(String moeilijkheidsgraad) {
        spelbord = new Spelbord();
        setMoeilijkheidsgraad(moeilijkheidsgraad);
    }

    public Spelbord getSpelbord() {
        return spelbord;
    }

    private void setMoeilijkheidsgraad(String moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }

    public boolean getIsGewonnen() {
        return isGewonnen;
    }

    public String getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }
    
    

    
}
