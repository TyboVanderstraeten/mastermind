/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author bramd
 */
public class Uitdaging {

    private int nummer;
    private Spel spel;
    private String uitdager;

    public Uitdaging(Spel spel, int nummer, String uitdager) {
        setNummer(nummer);
        setSpel(spel);
        setUitdager(uitdager);
    }

    public Spel getSpel() {
        return spel;
    }

    public String getUitdager() {
        return uitdager;
    }
    
    
    private void setNummer(int nummer) {
        this.nummer = nummer;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

    private void setUitdager(String uitdager) {
        this.uitdager = uitdager;
    }
    
}
