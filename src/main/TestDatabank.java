/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.sql.SQLException;
import persistentie.SpelerMapper;

public class TestDatabank { // ENKEL GEMAAKT OM TE TESTEN OF ER DATA IN DB KAN GEPOMPT WORDEN!!! --Tybo
    public static void main(String[] args) throws SQLException {
        SpelerMapper spelerMapper = new SpelerMapper();
        
        spelerMapper.voegSpelerToeHardcoded();
    }

}
