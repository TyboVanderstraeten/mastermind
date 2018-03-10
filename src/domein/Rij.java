package domein;

public abstract class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;

    public Rij() {        
        if (this.getClass().getSimpleName().equals("MakkelijkeNormaleRij")){
            codepinnen = new CodePin[4];
        }
        else{
            codepinnen = new CodePin[5];
        }
        evaluatiepinnen = new EvaluatiePin[codepinnen.length];
        
    }

    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[codepinnen.length];

        for (int i = 0; i < codepinnen.length; i++) {
            try {
                pinkleuren[i] = String.format("%6s",codepinnen[i].getKleur());

            } catch (NullPointerException e) {
                pinkleuren[i] = "X";
            }
        }
        return pinkleuren;
    }

    public void geefVolgendePoging(String[] poging) {
        for (int i = 0; i < codepinnen.length; i++) {
            codepinnen[i] = new CodePin(poging[i]);
        }
    }
}
