package domein;

public class Rij {

    private final Pin[] pinnen;

    public Rij(String moeilijkheidsgraad) {
        if (moeilijkheidsgraad.equals("moeilijk")) {
            pinnen = new Pin[5];
        } else {
            pinnen = new Pin[4];
        }
    }
    
    public String[] geefPinkleuren(){
        String[] pinkleuren = new String[pinnen.length];
        
        for(int i =0; i<pinnen.length; i++){
            pinkleuren[i] = pinnen[i].getKleur();
        }
        return pinkleuren;
    }
}
