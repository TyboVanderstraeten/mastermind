

package domein;

public class Spel {
    private final Spelbord spelbord;
    private String moeilijkheidsgraad;
    private String willekeurigeCode;
    
    public Spel(String moeilijkheidsgraad){
        spelbord = new Spelbord();            
        
    }

    public Spelbord getSpelbord() {
        return spelbord;
    }

    public void setMoeilijkheidsgraad(String moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }
    
    
}
