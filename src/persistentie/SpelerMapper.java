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
    private static final String GEEF_TEGENSPELERS = "SELECT spelersnaam FROM ID222177_g68.Speler WHERE ? >= ? AND spelersnaam <> ?";
    private static final String UPDATE_AANTALGEWONNEN = "UPDATE ID222177_g68.Speler SET aantalGewonnenMakkelijk = ?, aantalGewonnenNormaal = ?, aantalGewonnenMoeilijk = ? WHERE spelersnaam = ?";
    //Klassement SQL query's
    private static final String GEEF_SPELERSKLASSEMENTMAKKELIJK = "SELECT spelersnaam, aantalPuntenMakkelijk FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenMakkelijk > 0 ORDER BY aantalPuntenMakkelijk DESC";
    private static final String GEEF_SPELERSKLASSEMENTNORMAAL = "SELECT spelersnaam, aantalPuntenNormaal FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenNormaal > 0 ORDER BY aantalPuntenNormaal DESC";
    private static final String GEEF_SPELERSKLASSEMENTMOEILIJK = "SELECT spelersnaam, aantalPuntenMoeilijk FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenMoeilijk > 0 ORDER BY aantalPuntenMoeilijk DESC";
    
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
                    int[] aantalGewonnenUitdagingen = new int[3];
                    aantalGewonnen[0] = rs.getInt("aantalGewonnenMakkelijk");
                    aantalGewonnen[1] = rs.getInt("aantalGewonnenNormaal");
                    aantalGewonnen[2] = rs.getInt("aantalGewonnenMoeilijk");
                    aantalGewonnenUitdagingen[0] = rs.getInt("aantalGewonnenUitdagingenMakkelijk");
                    aantalGewonnenUitdagingen[1] = rs.getInt("aantalGewonnenUitdagingenNormaal");
                    aantalGewonnenUitdagingen[2] = rs.getInt("aantalGewonnenUitdagingenMoeilijk");
                    speler = new Speler(spelersnaam, wachtwoord, aantalGewonnen,aantalGewonnenUitdagingen);
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

    public List<String> geefTegenspelers(String naamUitdagingenCategorie, int aantalGewonnenUitdagingen, String spelersnaam) {
        List<String> tegenspelers = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_TEGENSPELERS)) {
            query.setString(1, naamUitdagingenCategorie);
            query.setInt(2, aantalGewonnenUitdagingen);
            query.setString(3, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String spelersnaamForAdd = rs.getString("spelersnaam");
                    tegenspelers.add(spelersnaamForAdd);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return tegenspelers;
    }

}
