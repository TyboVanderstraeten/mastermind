package domein;

public class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;

    public Rij(int moeilijkheidsgraad) {
        evaluatiepinnen = new EvaluatiePin[4];
        if (moeilijkheidsgraad == 1 || moeilijkheidsgraad == 2) {
            codepinnen = new CodePin[4];
        } else {
            codepinnen = new CodePin[5];
        }

        for (int i = 0; i < codepinnen.length; i++) {
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
