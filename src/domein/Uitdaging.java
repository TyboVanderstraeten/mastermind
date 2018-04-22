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

    public Uitdaging(Spel spel, int nummer) {
        setNummer(nummer);
        setSpel(spel);
    }

    public Spel getSpel() {
        return spel;
    }

    private void setNummer(int nummer) {
        this.nummer = nummer;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

}
