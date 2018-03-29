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
import java.util.Arrays;
import java.util.List;

/**
 * Zorgt voor de connectie met de Spel objecten uit de databank.
 *
 */
public class SpelMapper {

    private static final String INSERT_SPEL = "INSERT INTO ID222177_g68.Spel (spelnaam, spelersnaam, aantalPogingen, moeilijkheidsgraad) VALUES (?,?,?,?)";
    private static final String GEEF_SPELLEN = "SELECT * FROM ID222177_g68.Spel";
    private static final String VERWIJDER_SPEL = "DELETE * FROM ID222177_g68.Spel WHERE spelnaam = ?";

    private static final String INSERT_RIJ = "INSERT INTO ID222177_g68.Rij (rijNummer, spelnaam, spelersnaam, combinatie) VALUES (?,?,?,?)";

    public void voegSpelToe(String spelnaam, String spelersnaam, Spel spel) {               //moet nog aangepast worden
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement querySpel = conn.prepareStatement(INSERT_SPEL);
                PreparedStatement queryRij = conn.prepareStatement(INSERT_RIJ)) {
            querySpel.setString(1, spelnaam);
            querySpel.setString(2, spelersnaam);
            System.out.println(spel.getSpelbord().getAantalPogingen());
            querySpel.setInt(3, spel.getSpelbord().getAantalPogingen());
            querySpel.setString(4, spel.getClass().getSimpleName());

            for (int i = 0; i < spel.getSpelbord().getAantalPogingen(); i++) {
                queryRij.setInt(1, i);
                queryRij.setString(2, spelnaam);
                queryRij.setString(3, spelersnaam);

                queryRij.setString(4, Arrays.toString(Arrays.copyOfRange(spel.getSpelbord().getRijen()[i].geefPinkleuren(), 0, spel.getClass().getSimpleName().equals("MoeilijkSpel") ? 5 : 4)).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", ""));

            }
            querySpel.executeUpdate();
            queryRij.executeUpdate();
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

    public List<Spel> geefSpellen() {
        List<Spel> spellen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELLEN);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String spelnaam = rs.getString("spelnaam");
                String spelersnaam = rs.getString("spelersnaam");
                String willekeurigeCode = rs.getString("willekeurigeCode");
                String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");

                if (moeilijkheidsgraad == "makkelijk") {
                    spellen.add(new MakkelijkSpel(spelnaam, spelersnaam, willekeurigeCode));
                } else if (moeilijkheidsgraad == "normaal") {
                    spellen.add(new NormaalSpel(spelnaam, spelersnaam, willekeurigeCode));
                } else if (moeilijkheidsgraad == "moeilijk") {
                    spellen.add(new MoeilijkSpel(spelnaam, spelersnaam, willekeurigeCode));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spellen;
    }

    public void verwijderSpel(String spelnaam) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(VERWIJDER_SPEL)) {
            query.setString(1, spelnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


//SUBKLASSES IN DATABASE NODIG
