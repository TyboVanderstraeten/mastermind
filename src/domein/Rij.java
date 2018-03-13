package domein;

public abstract class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;

    public Rij() {
        if (this.getClass().getSimpleName().equals("MakkelijkeNormaleRij")) {
            codepinnen = new CodePin[4];
        } else {
            codepinnen = new CodePin[5];
        }
        evaluatiepinnen = new EvaluatiePin[codepinnen.length];

    }

    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[codepinnen.length + evaluatiepinnen.length+1];     //+1 omdat ik een plek per rij bijhoudt voor "Evaluatie"
        for (int i = 0; i < codepinnen.length; i++) {
            if (codepinnen[i] == null) {
                pinkleuren[i] = "X";
            } else {
                pinkleuren[i] = String.format("%6s", codepinnen[i].getKleur());
            }
        }
        
        pinkleuren[codepinnen.length]= "\t\tEvaluatie:";
        for (int i = codepinnen.length+1; i < codepinnen.length + evaluatiepinnen.length+1; i++) {
            if (evaluatiepinnen[i - codepinnen.length-1] == null) {
                pinkleuren[i] = "X";
            } else {
                pinkleuren[i] = String.format("%5s", evaluatiepinnen[i].getKleur());
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
