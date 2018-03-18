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

/**
 * Zorgt voor de connectie met de Spel objecten uit de databank.
 *
 */
public class SpelMapper {

    private static final String INSERT_SPEL = "INSERT INTO ID222177_g68.Spel (spelersnaam, wachtwoord) VALUES (?,?)";

    public void voegSpelToe(String spelnaam, String spelersnaam, Spel spel) {               //moet nog aangepast worden
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPEL)) {
            query.setString(1, spelnaam);
            query.setInt(2, spel.getSpelbord().getAantalPogingen());
            query.setString(3, spelersnaam);
            query.setString(4, spel.getClass().getSimpleName());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
//    public Spel geefSpel(String spelnaam) {  
//        Spel spel = null;
//
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g68.spel WHERE spelnaam = ?")) {
//            query.setString(1, spelnaam);
//            try (ResultSet rs = query.executeQuery()) {
//                if (rs.next()) {
//                    String willekeurigeCode = rs.getString("willekeurigeCode");
//                    spel = new MakkelijkSpel(spelnaam, willekeurigeCode);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return spel;
//    }
//
//    public List<Spel> geefSpellen() {
//        List<Spel> spellen = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g68.spel");
//                ResultSet rs = query.executeQuery()) {
//
//            while (rs.next()) {
//                String spelersnaam = rs.getString("spelersnaam");
//                String willekeurigeCode = rs.getString("willekeurigeCode");                
//
//                spellen.add(new MakkelijkSpel(spelersnaam, willekeurigeCode));   
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return spellen;
//    }
}

//SUBKLASSES IN DATABASE NODIG
