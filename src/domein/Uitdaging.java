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

    private int id;
    private Spel spel;
    private String uitdager;

    public Uitdaging(Spel spel, int id, String uitdager) {
        setId(id);
        setSpel(spel);
        setUitdager(uitdager);
    }

    public Spel getSpel() {
        return spel;
    }

    public String getUitdager() {
        return uitdager;
    }
    
    public int getId(){
        return id;
    }
    
    
    private void setId(int id) {
        this.id = id;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

    private void setUitdager(String uitdager) {
        this.uitdager = uitdager;
    }
    
}
