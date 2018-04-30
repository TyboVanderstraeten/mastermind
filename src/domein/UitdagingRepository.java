/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.UitdagingMapper;

/**
 *
 * @author bramd
 */
public class UitdagingRepository {
    
    private final UitdagingMapper mapper;

    public UitdagingRepository() {
        mapper = new UitdagingMapper();
    }

    public void registreerUitdaging(String speler1, String speler2, Spel spel) {
        mapper.registreerUitdaging(speler1, speler2, spel);
    }

    public String[][] geefUitdagingen(String spelersnaam) {
        List<String[]> uitdagingInfo = mapper.geefLijstUitdagingen(spelersnaam);
        String[][] uitdagingen = new String[uitdagingInfo.size()][];
        for (int i = 0; i < uitdagingen.length; i++) {
            uitdagingen[i] = uitdagingInfo.get(i);
        }
        return uitdagingen;

    }
    
    public String geefOpenUitdaging(String spelersnaam){
        return mapper.geefOpenUitdaging(spelersnaam);
    }

    public Uitdaging laadUitdaging(String spelersnaam) {
        return mapper.laadUitdaging(spelersnaam);
    }
    
    public void updateIsHuidigeSpeler1(String spelersnaam){
        mapper.updateIsHuidigeSpeler1(spelersnaam);
    }
    
    public void updateIsHuidigeSpeler2(String spelersnaam, boolean huidig){
        mapper.updateIsHuidigeSpeler2(spelersnaam, huidig);
    }
    
    public int geefAantalPogingen(int nummer){
        return mapper.geefAantalPogingen(nummer);
    }

    public void voegAantalPogingenToe(int aantalPogingen, int nummer){
        mapper.voegAantalPogingenToe(aantalPogingen, nummer);
    }
}
