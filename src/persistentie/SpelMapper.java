package persistentie;

import domein.MakkelijkSpel;
import domein.NormaalSpel;
import domein.MoeilijkSpel;
import domein.Spel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpelMapper {

    /**public Spel geefSpel(String spelnaam) {  
        Spel spel = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g68.spel WHERE spelnaam = ?")) {
            query.setString(1, spelnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String willekeurigeCode = rs.getString("willekeurigeCode");
                    spel = new MakkelijkSpel(spelnaam, willekeurigeCode);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spel;
    }

    public List<Spel> geefSpellen() {
        List<Spel> spellen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g68.spel");
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String spelersnaam = rs.getString("spelersnaam");
                String willekeurigeCode = rs.getString("willekeurigeCode");                

                spellen.add(new MakkelijkSpel(spelersnaam, willekeurigeCode));   
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spellen;
    }**/ //voorlopig niet nodig
}

//SUBKLASSES IN DATABASE NODIG
