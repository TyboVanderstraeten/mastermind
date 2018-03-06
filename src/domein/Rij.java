package domein;

public class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;
    public Rij() {
        codepinnen = new CodePin[5];
        evaluatiepinnen = new EvaluatiePin[4];
        for (int i = 0; i < 5; i++) {
            codepinnen[i] = new CodePin();
        }
    }

    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[codepinnen.length];

        for (int i = 0; i < codepinnen.length; i++) {
            pinkleuren[i] = codepinnen[i].getKleur();
        }
        return pinkleuren;
    }
}
