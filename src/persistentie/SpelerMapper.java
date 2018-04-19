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
    //uitdagingen SQL query's
    private static final String UPDATE_AANTALGEWONNENUITDAGINGEN = "UPDATE ID222177_g68.Speler SET aantalGewonnenUitdagingenMakkelijk = ?, aantalGewonnenUitdagingenNormaal = ?, aantalGewonnenUitdagingenMoeilijk = ? WHERE spelersnaam = ?";
    private static final String UPDATE_AANTALPUNTEN = "UPDATE ID222177_g68.Speler SET aantalPuntenMakkelijk = ?, aantalPuntenNormaal = ?, aantalPuntenMoeilijk = ? WHERE spelersnaam = ?";
    private static final String UPDATE_AANTALGESPEELDEUITDAGINGEN = "UPDATE ID222177_g68.Speler SET aantalGespeeldeUitdagingenMakkelijk = ?, aantalGespeeldeUitdagingenNormaal = ?, aantalGespeeldeUitdagingenMoeilijk = ? WHERE spelersnaam = ?";
    private static final String GEEF_AANTALGEWONNENUITDAGINGEN = "SELECT AantalGewonnenUitdagingen FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_AANTALGESPEELDEUITDAGINGEN = "SELECT AantalGespeeldeUitdagingen FROM ID222177_g68.Speler WHERE spelersnaam = ?";
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
                    speler = new Speler(spelersnaam, wachtwoord, aantalGewonnen, aantalGewonnenUitdagingen);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }
    
    public int[] geefAantalGewonnenUitdagingen(String spelersnaam) {
        int[] aantalGewonnenUitdagingen = new int[3];

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    aantalGewonnenUitdagingen[0] = rs.getInt("aantalGewonnenUitdagingenMakkelijk");
                    aantalGewonnenUitdagingen[1] = rs.getInt("aantalGewonnenUitdagingenNormaal");
                    aantalGewonnenUitdagingen[2] = rs.getInt("aantalGewonnenUitdagingenMoeilijk");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return aantalGewonnenUitdagingen;
    }
    
    public int[] geefAantalGespeeldeUitdagingen(String spelersnaam) {
        int[] aantalGespeeldeUitdagingen = new int[3];
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AANTALGESPEELDEUITDAGINGEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    aantalGespeeldeUitdagingen[0] = rs.getInt("aantalGespeeldeUitdagingenMakkelijk");
                    aantalGespeeldeUitdagingen[1] = rs.getInt("aantalGespeeldeUitdagingenNormaal");
                    aantalGespeeldeUitdagingen[2] = rs.getInt("aantalGespeeldeUitdagingenMoeilijk");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return aantalGespeeldeUitdagingen;
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
    
    public void updateAantalGewonnenUitdagingen(String spelersnaam, int aantalGewonnenUitdagingenMakkelijk, int aantalGewonnenUitdagingenNormaal, int aantalGewonnenUitdagingenMoeilijk) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALGEWONNENUITDAGINGEN)) {
            query.setInt(1, aantalGewonnenUitdagingenMakkelijk);
            query.setInt(2, aantalGewonnenUitdagingenNormaal);
            query.setInt(3, aantalGewonnenUitdagingenMoeilijk);
            query.setString(4, spelersnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updateAantalGespeeldeUitdagingen(String spelersnaam, int aantalGespeeldeUitdagingenMakkelijk, int aantalGespeeldeUitdagingenNormaal, int aantalGespeeldeUitdagingenMoeilijk) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALGESPEELDEUITDAGINGEN)) {
            query.setInt(1, aantalGespeeldeUitdagingenMakkelijk);
            query.setInt(2, aantalGespeeldeUitdagingenNormaal);
            query.setInt(3, aantalGespeeldeUitdagingenMoeilijk);
            query.setString(4, spelersnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updateAantalPunten(String spelersnaam, int aantalPuntenMakkelijk, int aantalPuntenNormaal, int aantalPuntenMoeilijk) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALPUNTEN)) {
            query.setInt(1, aantalPuntenMakkelijk);
            query.setInt(2, aantalPuntenNormaal);
            query.setInt(3, aantalPuntenMoeilijk);
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
    
    //KLASSEMENT
    public List<String[]> geefKlassementMakkelijk() {
        List<String[]> klassementMakkelijk = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTMAKKELIJK)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenMakkelijk"));

                    klassementMakkelijk.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementMakkelijk;
    }

    public List<String[]> geefKlassementNormaal() {
        List<String[]> klassementNormaal = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTNORMAAL)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenNormaal"));

                    klassementNormaal.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementNormaal;
    }

    public List<String[]> geefKlassementMoeilijk() {
        List<String[]> klassementMoeilijk = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTMOEILIJK)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenMoeilijk"));

                    klassementMoeilijk.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementMoeilijk;
    }
}
