
package domein;

public class NormaalSpel extends Spel{
    private static int aantalGewonnen = 0;
    
    public NormaalSpel(int moeilijkheidsgraad){
        super(moeilijkheidsgraad);        
    }

    public static int getAantalGewonnen() {
        return aantalGewonnen;
    }
    
    
    
}
