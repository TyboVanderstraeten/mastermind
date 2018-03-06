
package domein;


public class MakkelijkSpel extends Spel{
    private static int aantalGewonnen =0;
    
    public MakkelijkSpel(int moeilijkheidsgraad){
        super(moeilijkheidsgraad);
    }  
    public MakkelijkSpel(String spelnaam, String code){
        super(spelnaam, code);
    }

    public static int getAantalGewonnen() {
        return aantalGewonnen;
    }
    
    
    
    
    
}
