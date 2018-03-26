    package domein;

/**
 * Abstracte superklasse van MakkelijkSpel, NormaalSpel en MoeilijkSpel.
 *
 */
public abstract class Spel {

    private final Spelbord spelbord;
    private String spelnaam;

    /**
     * Class constructor. Genereert de random code (de oplossing) en roept dan
     * de constructor van Spelbord aan die de code meekrijgt als parameter. De
     * code wordt ook in spel opgeslagen.
     */
    public Spel() {
        this.spelbord = new Spelbord(genereerWillekeurigeCode());
    }

    /**
     * Abstracte klasse die in de subklasses een willekeurige code zal genereren
     * afhankelijk van de moeilijkheidsgraad.
     *
     * @return
     */
    protected abstract int[] genereerWillekeurigeCode();

    /**
     * Getter geeft het attribuut spelbord terug.
     *
     * @return
     */
    public Spelbord getSpelbord() {
        return spelbord;
    }
    
    public String getSpelnaam(){
        return spelnaam;
    }

    //private void setSpelnaam(String spelnaam) {
    //    this.spelnaam = spelnaam;                 //nog niet nodig
    //}
}

//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.
