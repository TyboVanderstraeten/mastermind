package persistentie;

import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SpelerMapper {

    private static final String INSERT_SPELER = "INSTERT INTO ID222177_g68.speler (spelersnaam, wachtwoord)" + "VALUES (?,?)";

    public void voegSpelerToe(Speler speler) {               //moet nog aangepast worden
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getSpelersnaam());
            query.setString(2, speler.getWachtwoord());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Speler geefSpeler(String spelersnaam) {
//        Speler speler = null;
//        
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g68.speler WHERE spelersnaam = ?")) {
//            query.setString(1, spelersnaam);
//            try (ResultSet rs = query.executeQuery()) {
//                if (rs.next()) {
//                    String wachtwoord = rs.getString("wachtwoord");
//
//                    speler = new Speler(spelersnaam, wachtwoord);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
        Speler speler = new Speler("bob", "appel");
        return speler;
    }

}
