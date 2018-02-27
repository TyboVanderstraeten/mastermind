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
}
