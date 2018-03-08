package domein;

public class MoeilijkSpel extends Spel{
    private static int aantalGewonnen =0;
    
    public MoeilijkSpel(int moeilijkheidsgraad){
        super(moeilijkheidsgraad);
    }

    public static int getAantalGewonnen() {
        return aantalGewonnen;
    }       
}
