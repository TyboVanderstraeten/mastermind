package domein;

public class Rij {

    private final Pin[] pinnen;

    public Rij() {
        pinnen = new Pin[5];
        for (int i = 0; i < 5; i++) {
            pinnen[i] = new Pin();
        }
    }

    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[pinnen.length];

        for (int i = 0; i < pinnen.length; i++) {
            pinkleuren[i] = pinnen[i].getKleur();
        }
        return pinkleuren;
    }
}
