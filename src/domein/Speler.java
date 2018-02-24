
package domein;


public class Speler {
    private String spelersnaam;
    private String wachtwoord;
    
    public Speler(String spelersnaam, String wachtwoord){
        setSpelersnaam(spelersnaam);
        setWachtwoord(wachtwoord);
    }
   
    
    public String getSpelersnaam() {
        return spelersnaam;
    }

    private void setSpelersnaam(String spelersnaam) {
        this.spelersnaam = spelersnaam;
    }

    private void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    
    
    
    
    
    
}
