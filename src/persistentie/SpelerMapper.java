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
    private static final String GEEF_SPELERSKLASSEMENTMAKKELIJK = "SELECT spelersnaam, aantalGespeeldUitdagingenMakkelijk, aantalGewonnenUitdagingenMakkelijk FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenMakkelijk > 0 ORDER BY aantalGewonnenUitdagingenMakkelijk DESC";
    private static final String GEEF_SPELERSKLASSEMENTNORMAAL = "SELECT spelersnaam, aantalGespeeldUitdagingenNormaal, aantalGewonnenUitdagingenNormaal FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenNormaal > 0 ORDER BY aantalGewonnenUitdagingenNormaal DESC";
    private static final String GEEF_SPELERSKLASSEMENTMOEILIJK = "SELECT spelersnaam, aantalGespeeldUitdagingenMoeilijk, aantalGewonnenUitdagingenMoeilijk FROM ID222177_g68.Speler WHERE aantalGespeeldUitdagingenMoeilijk > 0 ORDER BY aantalGewonnenUitdagingenMoeilijk DESC";
    //uitdagingen SQL query's
    private static final String UPDATE_AANTALGEWONNENUITDAGINGEN = "UPDATE ID222177_g68.Speler SET aantalGewonnenUitdagingenMakkelijk = ?, aantalGewonnenUitdagingenNormaal = ?, aantalGewonnenUitdagingenMoeilijk = ? WHERE spelersnaam = ?";
    
    private static final String UPDATE_AANTALGESPEELDUITDAGINGEN = "UPDATE ID222177_g68.Speler SET aantalGespeeldUitdagingenMakkelijk = ?, aantalGespeeldUitdagingenNormaal = ?, aantalGespeeldUitdagingenMoeilijk = ? WHERE spelersnaam = ?";
    private static final String GEEF_AANTALGEWONNENUITDAGINGEN = "SELECT AantalGewonnenUitdagingen FROM ID222177_g68.Speler WHERE spelersnaam = ?";
    private static final String GEEF_AANTALGESPEELDUITDAGINGEN = "SELECT AantalGespeeldUitdagingen FROM ID222177_g68.Speler WHERE spelersnaam = ?";    
    

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
                    //int[] aantalPunten = new int[3];
                    int[] aantalGespeeldUitdagingen = new int[3];
                    aantalGewonnen[0] = rs.getInt("aantalGewonnenMakkelijk");
                    aantalGewonnen[1] = rs.getInt("aantalGewonnenNormaal");
                    aantalGewonnen[2] = rs.getInt("aantalGewonnenMoeilijk");                    
                    aantalGewonnenUitdagingen[0] = rs.getInt("aantalGewonnenUitdagingenMakkelijk");
                    aantalGewonnenUitdagingen[1] = rs.getInt("aantalGewonnenUitdagingenNormaal");
                    aantalGewonnenUitdagingen[2] = rs.getInt("aantalGewonnenUitdagingenMoeilijk");
//                    aantalPunten[0] = rs.getInt("aantalPuntenMakkelijk");
//                    aantalPunten[1] = rs.getInt("aantalPuntenNormaal");
//                    aantalPunten[2] = rs.getInt("aantalPuntenMoeilijk");
                    aantalGespeeldUitdagingen[0] = rs.getInt("aantalGespeeldUitdagingenMakkelijk");
                    aantalGespeeldUitdagingen[1] = rs.getInt("aantalGespeeldUitdagingenNormaal");
                    aantalGespeeldUitdagingen[2] = rs.getInt("aantalGespeeldUitdagingenNormaal");
                    speler = new Speler(spelersnaam, wachtwoord, aantalGewonnen, aantalGewonnenUitdagingen, aantalGespeeldUitdagingen);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    /**
     * Deze methode geeft het aantalGewonnenUitdagingen per moeilijkheidsgraad van een bepaalde speler terug.
     * 
     * @param spelersnaam de naam van de speler.
     * 
     * @return een int[] met het aantal gewonnen uitdagingen per moeilijkheidsgraad.
     */
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

    /**
     * Deze methode geeft het aantal gespeeld uitdagingen per moeilijkheidsgraad van een 
     * bepaalde speler terug.
     * 
     * @param spelersnaam de naam van de speler.
     * 
     * @return het aantal gespeelde uitdagingen per moeilijkheidsgraad.
     */
    public int[] geefAantalGespeeldeUitdagingen(String spelersnaam) {
        int[] aantalGespeeldeUitdagingen = new int[3];
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AANTALGESPEELDUITDAGINGEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    aantalGespeeldeUitdagingen[0] = rs.getInt("aantalGespeeldUitdagingenMakkelijk");
                    aantalGespeeldeUitdagingen[1] = rs.getInt("aantalGespeeldUitdagingenNormaal");
                    aantalGespeeldeUitdagingen[2] = rs.getInt("aantalGespeeldUitdagingenMoeilijk");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return aantalGespeeldeUitdagingen;
    }

    //Onderstaande methode is voor singleplayer spellen
    /**
     * Deze methode update het aantal gewonnen spellen van een bepaalde speler.
     * 
     * @param spelersnaam de naam van de speler.
     * @param aantalGewonnenMakkelijk het aantal gewonnen makkelijke spellen.
     * @param aantalGewonnenNormaal het aantal gewonnen normale spellen.
     * @param aantalGewonnenMoeilijk het aantal gewonnen moeilijke spellen.
     */
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

    
/**
 * Deze methode update het aantal gewonnen uitdagingen van een bepaalde speler.
 * 
 * @param spelersnaam de naam van de speler.
 * @param aantalGewonnenUitdagingenMakkelijk het aantal gewonnen makkelijke uitdagingen.
 * @param aantalGewonnenUitdagingenNormaal het aantal gewonnen normale uitdagingen.
 * @param aantalGewonnenUitdagingenMoeilijk het aantal gewonnen moeilijke uitdagingen.
 */
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

    /**
     * Deze methode update het aantal gespeelde uitdagingen.
     * 
     * @param spelersnaam de naam van de speler.
     * @param aantalGespeeldeUitdagingenMakkelijk het aantal gespeelde makkelijke uitdagingen.
     * @param aantalGespeeldeUitdagingenNormaal het aantal gespeelde normale uitdagingen.
     * @param aantalGespeeldeUitdagingenMoeilijk het aantal gespeelde moeilijke uitdagingen.
     */
    public void updateAantalGespeeldeUitdagingen(String spelersnaam, int aantalGespeeldeUitdagingenMakkelijk, int aantalGespeeldeUitdagingenNormaal, int aantalGespeeldeUitdagingenMoeilijk) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALGESPEELDUITDAGINGEN)) {
            query.setInt(1, aantalGespeeldeUitdagingenMakkelijk);
            query.setInt(2, aantalGespeeldeUitdagingenNormaal);
            query.setInt(3, aantalGespeeldeUitdagingenMoeilijk);
            query.setString(4, spelersnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    
/**
 * Deze methode geeft de mogelijke tegenspelers terug voor de meegegeven moeilijkheidsgraad.
 * 
 * @param naamUitdagingenCategorie de moeilijkheidsgraad
 * @param aantalGewonnenUitdagingen het aantal gewonnen uitdagingen.
 * @param spelersnaam de naam van de huidige speler.
 * 
 * @return een List van Strings die de spelersnamen van de mogelijke tegenspelers bevat.
 */
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
    /**
     * Deze methode zal het makkelijke klassement teruggeven.
     * 
     * @return een list van String[] die de spelnaam en het aantal punten bevat.
     */
    public List<String[]> geefKlassementMakkelijk() {
        List<String[]> klassementMakkelijk = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTMAKKELIJK)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    int score = rs.getInt("aantalGewonnenUitdagingenMakkelijk")*3 - (rs.getInt("aantalGespeeldUitdagingenMakkelijk")-rs.getInt("aantalGewonnenUitdagingenMakkelijk"));
                    infoSpelerKlassement[1] = String.format("%d", score);
                    //infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenMakkelijk"));

                    klassementMakkelijk.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementMakkelijk;
    }

    /**
     * Deze methode zal het normale klassement teruggeven.
     * 
     * @return een list van String[] die de spelnaam en het aantal punten bevat.
     */
    public List<String[]> geefKlassementNormaal() {
        List<String[]> klassementNormaal = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTNORMAAL)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    int score = rs.getInt("aantalGewonnenUitdagingenNormaal")*3 - (rs.getInt("aantalGespeeldUitdagingenNormaal")-rs.getInt("aantalGewonnenUitdagingenNormaal"));
                    infoSpelerKlassement[1] = String.format("%d", score);
                    //infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenNormaal"));

                    klassementNormaal.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementNormaal;
    }

    /**
     * Deze methode zal het moeilijke klassement teruggeven.
     * 
     * @return een list van String[] die de spelnaam en het aantal punten bevat.
     */
    public List<String[]> geefKlassementMoeilijk() {
        List<String[]> klassementMoeilijk = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELERSKLASSEMENTMOEILIJK)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] infoSpelerKlassement = new String[2];
                    infoSpelerKlassement[0] = rs.getString("spelersnaam");
                    int score = rs.getInt("aantalGewonnenUitdagingenMoeilijk")*3 - (rs.getInt("aantalGespeeldUitdagingenMoeilijk")-rs.getInt("aantalGewonnenUitdagingenMoeilijk"));
                    infoSpelerKlassement[1] = String.format("%d", score);
                    //infoSpelerKlassement[1] = String.format("%d", rs.getInt("aantalPuntenMoeilijk"));

                    klassementMoeilijk.add(infoSpelerKlassement);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return klassementMoeilijk;
    }
}
