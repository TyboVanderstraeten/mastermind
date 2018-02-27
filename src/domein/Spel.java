package domein;

public class Spel {

    private final Spelbord spelbord;
    private String moeilijkheidsgraad;
    private String willekeurigeCode;

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

}
