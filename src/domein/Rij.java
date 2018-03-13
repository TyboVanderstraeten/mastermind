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
        String[] evaluatiekleuren = new String[evaluatiepinnen.length];
        for (int i = 0; i < codepinnen.length; i++) {
            if(codepinnen[i]==null)
                pinkleuren[i]="X";
            else{            
                pinkleuren[i] = String.format("%6s",codepinnen[i].getKleur());            
            }
            if(evaluatiepinnen[i]==null)
                evaluatiekleuren[i]="X";
            else{
                evaluatiekleuren[i]= String.format("%5s", evaluatiepinnen[i].getKleur());
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
