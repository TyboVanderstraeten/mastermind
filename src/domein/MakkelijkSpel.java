
package domein;


public class MakkelijkSpel extends Spel{
    private static int aantalGewonnen =0;
    
    public MakkelijkSpel(int moeilijkheidsgraad){
        super(moeilijkheidsgraad);
    }      

    public static int getAantalGewonnen() {
        return aantalGewonnen;
    }
    
    
}
