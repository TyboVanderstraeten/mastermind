package domein;

public class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;

    public Rij() {
        evaluatiepinnen = new EvaluatiePin[4];
        codepinnen = new CodePin[4];
    }

    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[codepinnen.length];

        for (int i = 0; i < codepinnen.length; i++) {
            try {
                pinkleuren[i] = codepinnen[i].getKleur();

            } catch (NullPointerException e) {
                pinkleuren[i] = "X";
            }
        }
        return pinkleuren;
    }

    public void geefVolgendePoging(String[] poging) {
        for (int i = 0; i < codepinnen.length; i++) {
            codepinnen[i].veranderKleur(poging[i]);
        }
    }
}
