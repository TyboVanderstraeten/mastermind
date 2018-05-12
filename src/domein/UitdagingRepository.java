package domein;

import java.util.List;
import persistentie.UitdagingMapper;

/**
 *
 * Zorgt voor de verbinding met de UitdagingMapper uit de persistentielaag.
 */
public class UitdagingRepository {

    private final UitdagingMapper mapper;

    /**
     * Class constructor.
     * Bewaardt een nieuw object van UitdagingMapper als object.
     */
    public UitdagingRepository() {
        mapper = new UitdagingMapper();
    }

    /**
     * Deze methode zal de mapper aanroepen om een nieuwe uitdaging te registreren in de database.
     * 
     * @param speler1 de naam van de uitdager.
     * @param speler2 de naam van de tegenspeler.
     * @param spel  het spelObject.
     */
    public void registreerUitdaging(String speler1, String speler2, Spel spel) {
        mapper.registreerUitdaging(speler1, speler2, spel);
    }

    /**
     * Deze methode zal de mapper aanroepen om de nog niet aanvaarde uitdagingen weer te geven waarbij speler2 de spelersnaam van de parameter is.
     * 
     * @param spelersnaam de naam van speler2. (huidige speler)
     * 
     * @return een String[][] met de spelersnamen en moeilijkheidsgraden van de uitdagingen.
     */
    public String[][] geefUitdagingen(String spelersnaam) {
        List<String[]> uitdagingInfo = mapper.geefLijstUitdagingen(spelersnaam);
        String[][] uitdagingen = new String[uitdagingInfo.size()][];
        for (int i = 0; i < uitdagingen.length; i++) {
            uitdagingen[i] = uitdagingInfo.get(i);
        }
        return uitdagingen;

    }

    /**
     * Zal de mapper aanroepen om de nog niet afgewerkte uitdaging terug te geven.
     * 
     * @param spelersnaam de naam van de huidige speler.
     * 
     * @return de naam van de nog niet afgewerkte uitdaging.
     */
    public String geefOpenUitdaging(String spelersnaam) {
        return mapper.geefOpenUitdaging(spelersnaam);
    }

    /**
     * Zal de mapper aanroepen om een uitdaging te laden.
     * 
     * @param uitdager de naam van de uitdager.
     * @param spelersnaam de huidige spelersnaam (aanvaarder)
     * 
     * @return het SpelObject van de Uitdaging.
     */
    public Spel laadUitdaging(String uitdager, String spelersnaam) {
        return mapper.laadUitdaging(uitdager, spelersnaam);
    }

    /**
     * Zal de mapper aanroepen om ervoor te zorgen dat isAanvaard op 1 (true) wordt gezet.
     * 
     * @param id het id van de uitdaging (uniek).
     */
    public void aanvaardUitdaging(int id) {
        mapper.aanvaardUitdaging(id);
    }

//    public int geefAantalPogingen(int nummer, String spelersnaam){
//        return mapper.geefAantalPogingen(nummer, spelersnaam);
//    }
    /**
     * Zal de mapper aanroepen om het aantalPogingen en de naam van de tegenspeler terug te geven.
     * 
     * @param id het id van de uitdaging.
     * @param spelersnaam de naam van de huidige speler.
     * 
     * @return  een String[] die het aantalPogingen en de naam van de tegenspeler zal bevatten.
     */
    public String[] geefUitdagingInfo(int id, String spelersnaam) {
        return mapper.geefUitdagingInfo(id, spelersnaam);
    }

    /**
     * Zal het aantalPogingen van Speler1 toevoegen aan de databank.
     * 
     * @param aantalPogingen het aantal pogingen die nodig waren om het spel te voltooien.
     * 
     * @param id het id van de uitdaging.
     */
    public void voegAantalPogingenToeS1(int aantalPogingen, int id) {
        mapper.voegAantalPogingenToeS1(aantalPogingen, id);
    }

    /**
     * Zal het aantalPogingen van Speler2 toevoegen aan de databank.
     * 
     * @param aantalPogingen het aantal pogingen die nodig waren om het spel te voltooien.
     * 
     * @param id het id van de uitdaging.
     */
    public void voegAantalPogingenToeS2(int aantalPogingen, int id) {
        mapper.voegAantalPogingenToeS2(aantalPogingen, id);
    }

}
