package persistentie;

import domein.Speler;
import exceptions.SpelerNietUniekException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zorgt voor de connectie met de Spelerobjecten van de databank.
 *
 */
public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO ID222177_g68.Speler (spelersnaam, wachtwoord) VALUES (?,?)";
    private static final String GEEF_SPELER = "SELECT * FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_MAKKELIJKWINS = "SELECT aantalGewonnenMakkelijk FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_NORMAALWINS = "SELECT aantalGewonnenNormaal FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_MOEILIJKWINS = "SELECT aantalGewonnenMoeilijk FROM ID222177_g68.Speler WHERE spelersnaam = ?";

    /**
     * voegt het spelerobject dat meegegeven is als parameter toe aan de
     * databank.
     *
     * @param speler een object van Speler.
     * @see domein.Speler
     */
    public void voegSpelerToe(Speler speler) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getSpelersnaam());
            query.setString(2, speler.getWachtwoord());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new SpelerNietUniekException();
        }
    }

    /**
     * geeft een spelerobject terug dat overeenkomt met de spelernaam.
     *
     * @param spelersnaam de spelersnaam van de gebruiker
     * @return
     */
    public Speler geefSpeler(String spelersnaam) {
        Speler speler = null;

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String wachtwoord = rs.getString("wachtwoord");
                    speler = new Speler(spelersnaam, wachtwoord);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    public int geefMakkelijkeWins(String spelersnaam) {
        int wins = 0;
        try {
            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            PreparedStatement query = conn.prepareStatement(GEEF_MAKKELIJKWINS);
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    wins = rs.getInt("aantalGewonnenMakkelijk");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return wins;
    }

    public int geefNormaleWins(String spelersnaam) {
        int wins = 0;
        try {
            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            PreparedStatement query = conn.prepareStatement(GEEF_NORMAALWINS);
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    wins = rs.getInt("aantalGewonnenNormaal");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return wins;
    }

    public int geefMoeilijkeWins(String spelersnaam) {
        int wins = 0;
        try {
            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            PreparedStatement query = conn.prepareStatement(GEEF_MOEILIJKWINS);
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    wins = rs.getInt("aantalGewonnenMoeilijk");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return wins;
    }

}
