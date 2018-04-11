package persistentie;

import domein.Speler;
import exceptions.SpelerNietUniekException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Zorgt voor de connectie met de Spelerobjecten van de databank.
 *
 */
public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO ID222177_g68.Speler (spelersnaam, wachtwoord) VALUES (?,?)";
    private static final String GEEF_SPELER = "SELECT * FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_TEGENSPELERS = "SELECT DISTINCT spelersnaam FROM ID222177_g68.Spel WHERE moeilijkheidsgraad = ?";
    private static final String UPDATE_AANTALGEWONNEN = "UPDATE ID222177_g68.Speler SET aantalGewonnenMakkelijk = ?, aantalGewonnenNormaal = ?, aantalGewonnenMoeilijk = ? WHERE spelersnaam = ?";

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
                    int[] aantalGewonnen = new int[3];
                    aantalGewonnen[0] = rs.getInt("aantalGewonnenMakkelijk");
                    aantalGewonnen[1] = rs.getInt("aantalGewonnenNormaal");
                    aantalGewonnen[2] = rs.getInt("aantalGewonnenMoeilijk");
                    speler = new Speler(spelersnaam, wachtwoord, aantalGewonnen);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    public void updateSpelerAantalGewonnen(String spelersnaam, int aantalGewonnenMakkelijk, int aantalGewonnenNormaal, int aantalGewonnenMoeilijk) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALGEWONNEN)) {
                query.setInt(1, aantalGewonnenMakkelijk);
                query.setInt(2, aantalGewonnenNormaal);
                query.setInt(3, aantalGewonnenMoeilijk);
                query.setString(4, spelersnaam);
                query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<String> geefTegenspelers(int moeilijkheidsgraad)
    {
        List<String> tegenspelers = new ArrayList<>();
        
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_TEGENSPELERS)) {
                query.setString(1, moeilijkheidsgraad);
                try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String spelersnaam = rs.getString("spelersnaam");
                    tegenspelers.add(spelersnaam);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return tegenspelers;
    }

}
