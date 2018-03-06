/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cui.MastermindApplicatie;
import domein.DomeinController;

/**
 *
 * @author bramd
 */
public class startUp {
    public static void main(String[] args) {
        DomeinController domeinController = new DomeinController();
        MastermindApplicatie mastermindApplicatie = new MastermindApplicatie(domeinController);
        mastermindApplicatie.startApplicatie();
    }
}
