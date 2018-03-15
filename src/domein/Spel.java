package domein;

import java.security.SecureRandom;

/**
 * Abstracte superklasse van MakkelijkSpel, NormaalSpel en MoeilijkSpel.
 *
 */
public abstract class Spel {

    private final Spelbord spelbord;
    private final String[] willekeurigeCode;        //moet spel dit kennen? spelbord kent dit ook
    private String spelnaam;
    private int aantalPogingen;
    private String[] poging;

    /**
     * Class constructor. Genereert de random code (de oplossing) en roept dan
     * de constructor van Spelbord aan die de code meekrijgt als parameter. De
     * code wordt ook in spel opgeslagen.
     */
    public Spel() {
        genereerWillekeurigeCode();
    }

    protected abstract void genereerWillekeurigeCode();  
    
    
    /**
     * Getter geeft het attribuut spelbord terug.
     *
     * @return
     */
    public Spelbord getSpelbord() {
        return spelbord;
    }

    //private void setSpelnaam(String spelnaam) {
    //    this.spelnaam = spelnaam;                 //nog niet nodig
    //}
    /**
     * Setter Zorgt ervoor dat het attribuut poging de waarde krijgt van de
     * parameter.
     *
     * @param poging
     */
    public void setPoging(String[] poging) {
        this.poging = poging;
    }
    
    
}

//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.
