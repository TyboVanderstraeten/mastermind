package domein;

/**
 * Abstracte superklasse van MakkelijkeNormaleRij en MoeilijkeRij.
 *
 */
public abstract class Rij {

    private final Pin[] codepinnen;
    private final Pin[] evaluatiepinnen;

    /**
     * Class constructor. Geeft de arrays codepinnen en evaluatiepinnen de
     * juiste lengte afhankelijk van de moeilijkheidsgraad.
     *
     * @param codepinnen
     */
    public Rij(Pin[] codepinnen) {
        this.codepinnen = codepinnen;
        this.evaluatiepinnen = new EvaluatiePin[codepinnen.length];
    }

    /**
     * Geeft de pinkleuren van deze rij terug in de vorm van een array.
     *
     * @return
     */
    public String[] geefPinkleuren() {
        String[] pinkleuren = new String[codepinnen.length + evaluatiepinnen.length + 1];     //+1 omdat ik een plek per rij bijhoudt voor "Evaluatie'
        for (int i = 0; i < codepinnen.length; i++) {
            if (codepinnen[i] == null) {
                pinkleuren[i] = String.format("%-6s", "o");
            } else {
                pinkleuren[i] = String.format("%-6s", codepinnen[i].getKleur());
            }

        }

        pinkleuren[codepinnen.length] = "\t\tEvaluatie:";
        for (int i = codepinnen.length + 1; i < codepinnen.length + evaluatiepinnen.length + 1; i++) {
            if (evaluatiepinnen[i - codepinnen.length - 1] == null) {
                pinkleuren[i] = String.format("%-5s", "o");
            } else {
                pinkleuren[i] = String.format("%-5s", evaluatiepinnen[i - codepinnen.length - 1].getKleur());
            }
        }

        return pinkleuren;
    }

    //UC3
    /**
     * Roept de constructor van codePin aan die de juiste kleur meekrijgt als
     * parameter.
     *
     * @param poging de kleuren die de speler heeft ingegeven als poging.
     * @param willekeurigeCode code waaraan de pinnen moeten gelijk zijn om het
     * spel te winnen. Moet meegegeven worden want rij kent alleen de pinnen in
     * zijn eigen rij.
     */
    
    public abstract void geefPoging(String[] poging, String[] willekeurigeCode); //{
//        for (int i = 0; i < codepinnen.length; i++) {
//            codepinnen[i] = new CodePin(poging[i]);
//            if (willekeurigeCode[i].equals(poging[i])) {
//                evaluatiepinnen[i] = new EvaluatiePin("Rood");
//                continue;
//            }
//            for (int j = 0; j < evaluatiepinnen.length; j++) {
//                if (willekeurigeCode[i].equals(poging[j])) {
//                    evaluatiepinnen[i] = new EvaluatiePin("Wit");
//                }
//            }
//        }
//}

/**
 * Getter geeft het attribuut codepinnen terug.
 *
 * @return
 */
public Pin[] getCodepinnen() {
        return codepinnen;
    }

    public Pin[] getEvaluatiepinnen() {
        return evaluatiepinnen;
    }

    
}
