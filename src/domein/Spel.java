package domein;

import exceptions.MoeilijkheidsgraadKeuzeException;

/**
 * Abstracte superklasse van MakkelijkSpel, NormaalSpel en MoeilijkSpel.
 *
 */
public abstract class Spel {

    private final Spelbord spelbord;
    private String spelnaam;    
    private int id;

    /**
     * Class constructor. Genereert de random code (de oplossing) en roept dan
     * de constructor van Spelbord aan die de code meekrijgt als parameter. De
     * code wordt ook in spel opgeslagen.
     */
    public Spel() {
        this(null, 0);         //niet meer nodig sinds UC4 laden spel
    }

    /**
     * Class constructor.
     * Geeft default waarde aan id om zo de constructor met code en id aan te roepen.
     * 
     * @param code de te kraken code.
     */
    public Spel(int[] code) {
        this(code, 0);
    }

    /**
     * Class constructor.
     * Geeft het attribuut id de juiste waarde.
     * Als code niet niet null is krijgt het attribuut code deze waarde,
     * anders zal een nieuwe code gegenereert worden van de juiste moeilijkheidsgraad.
     * 
     * @param code de te kraken code.
     * @param id het id van de uitdaging.
     */
    public Spel(int[] code, int id) {
        this.id = id;        
        if (code == null) {
            this.spelbord = new Spelbord(genereerWillekeurigeCode());
        } else {
            this.spelbord = new Spelbord(code);
        }
    }

    /**
     * Abstracte klasse die in de subklasses een willekeurige code zal genereren
     * afhankelijk van de moeilijkheidsgraad.
     *
     * @return een int[] met de te kraken code.
     */
    protected abstract int[] genereerWillekeurigeCode();

    /**
     * Methode die de aanwezige moeilijkheidsgraden zal teruggeven.
     * 
     * @return int[] van alle moeilijkheidsgraden.
     */
    public int[] geefMoeilijkheidsgraden() {
        int[] moeilijkheidsgraden = new int[3];

        moeilijkheidsgraden[0] = 1;
        moeilijkheidsgraden[1] = 2;
        moeilijkheidsgraden[2] = 3;

        return moeilijkheidsgraden;
    }

    /**
     * Getter geeft het attribuut spelbord terug.
     *
     * @return spelbord object.
     */
    public Spelbord getSpelbord() {
        return spelbord;
    }

    /**
     * Getter.
     * Geeft het attribuut spelnaam terug.
     * 
     * @return naam van de speler. (spelnaam)
     */
    public String getSpelnaam() {
        return spelnaam;
    }

    /**
     * Getter.
     * Geeft het attribuut id terug.
     * 
     * @return id van de uitdaging. (0 indien het geen uitdaging is)
     */
    public int getId() {
        return id;
    }

    
}

//ATTRIBUTEN ISGEWONNEN EN MOEILIJKHEIDSGRAAD VERWIJDERT, ISGEWONNEN NIET NODIG, OOK NIET IN DATABANK, AANTAL GEWONNEN SPELLEN PER MOEILIJKHEIDSGRAAD WORDT GWN ZO OPGESLAGEN IN DE RESPECTIEVELIJKE KLASSE
//MOEILIJKHEIDSGRAAD HOEFT NIET ALS ATTRIBUUT WANT ER ZIJN SUBKLASSES DIE DE MOEILIJKHEIDSGRAAD REPRESENTEREN.
