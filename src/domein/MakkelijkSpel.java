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
public class MakkelijkSpel extends Spel{
    private static int aantalGewonnen;
    
    public MakkelijkSpel(int moeilijkheidsgraad){
        super(moeilijkheidsgraad);
    }  
    public MakkelijkSpel(String spelnaam, String code){
        super(spelnaam, code);
    }
    
}
