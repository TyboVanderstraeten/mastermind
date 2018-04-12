package persistentie;

import domein.MakkelijkSpel;
import domein.MakkelijkeRij;
import domein.MoeilijkSpel;
import domein.NormaalSpel;
import domein.Pin;
import domein.Rij;
import domein.Spel;
import exceptions.SpelnaamNietUniekException;
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

    private static final String INSERT_SPEL = "INSERT INTO ID222177_g68.Spel (spelnaam, spelersnaam, aantalPogingen, moeilijkheidsgraad, tegenspeler) VALUES (?,?,?,?,?)";
    private static final String INSERT_RIJ = "INSERT INTO ID222177_g68.Rij (rijNummer, spelnaam, spelersnaam, combinatie) VALUES (?,?,?,?)";
    private static final String GEEF_SPELLEN = "SELECT spelnaam FROM ID222177_g68.Spel where spelersnaam = ?";
    private static final String GEEF_SPEL = "SELECT * FROM ID222177_g68.Spel WHERE spelersnaam = ? AND spelnaam = ?";
    private static final String GEEF_RIJEN = "SELECT * FROM ID222177_g68.Rij WHERE spelersnaam = ? AND spelnaam = ?";
    private static final String VERWIJDER_SPEL = "DELETE * FROM ID222177_g68.Spel WHERE spelnaam = ? AND spelersnaam = ?";
    private static final String VERWIJDER_RIJ = "DELETE * FROM ID2221777_g68.Rij WHERE spelnaam = ? AND spelersnaam = ?";
    private static final String UPDATE_SPEL = "UPDATE ID222177_g68.Spel SET isUitdaging = 1 WHERE spelnaam = ? AND spelersnaam = ?";    
    private static final String GEEF_UITDAGINGEN = "SELECT spelnaam, moeilijkheidsgraad FROM ID222177_g68.Spel WHERE spelersnaam = ? AND tegenspeler is not null";

    public void voegSpelToe(String spelnaam, String spelersnaam, Spel spel, String tegenspeler) {               //moet nog aangepast worden//EDIT: DONE
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement querySpel = conn.prepareStatement(INSERT_SPEL);
                PreparedStatement queryRij = conn.prepareStatement(INSERT_RIJ)) {
            querySpel.setString(1, spelnaam);
            querySpel.setString(2, spelersnaam);
            querySpel.setInt(3, spel.getSpelbord().getAantalPogingen());
            querySpel.setString(4, spel.getClass().getSimpleName());
            querySpel.setString(5, tegenspeler);
            querySpel.executeUpdate();
            for (int i = 0; i < spel.getSpelbord().getAantalPogingen(); i++) {
                queryRij.setInt(1, i);
                queryRij.setString(2, spelnaam);
                queryRij.setString(3, spelersnaam);
                queryRij.setString(4, Arrays.toString(Arrays.copyOfRange(spel.getSpelbord().getRijen()[i].geefPinkleuren(), 0, spel.getClass().getSimpleName().equals("MoeilijkSpel") ? 5 : 4)).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", ""));
                queryRij.executeUpdate();
            }

            queryRij.setInt(1, spel.getSpelbord().getRijen().length - 1);
            queryRij.setString(2, spelnaam);
            queryRij.setString(3, spelersnaam);
            queryRij.setString(4, Arrays.toString(Arrays.copyOfRange(spel.getSpelbord().getRijen()[spel.getSpelbord().getRijen().length - 1].geefPinkleuren(), 0, spel.getClass().getSimpleName().equals("MoeilijkSpel") ? 5 : 4)).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", ""));
            queryRij.executeUpdate();
        } catch (SQLException ex) {
            throw new SpelnaamNietUniekException();
        }
    }
    
    public void spelIsUitdaging(String spelnaam, String spelersnaam) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_SPEL)) {
            query.setString(1, spelnaam);
            query.setString(2, spelersnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> geefSpelnamen(String spelersnaam) {
        List<String> spelnamen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELLEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String spelnaam = rs.getString("spelnaam");
                    spelnamen.add(spelnaam);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelnamen;
    }

    public void verwijderSpel(String spelnaam, String spelersnaam) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement spelQuery = conn.prepareStatement(VERWIJDER_SPEL);
                PreparedStatement rijQuery = conn.prepareStatement(VERWIJDER_RIJ)) {
            spelQuery.setString(1, spelnaam);
            spelQuery.setString(2, spelersnaam);
            spelQuery.executeUpdate();
            rijQuery.setString(1, spelnaam);
            rijQuery.setString(2, spelersnaam);
            rijQuery.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Spel laadSpel(String spelnaam, String spelersnaam) {
        Spel spel = null;
        List<int[]> rijen = new ArrayList<>();
        String niveau = "";
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPEL);
                PreparedStatement queryRij = conn.prepareStatement(GEEF_RIJEN)) {
            query.setString(1, spelersnaam);
            query.setString(2, spelnaam);
            query.setInt(3, 1);
            queryRij.setString(1, spelersnaam);
            queryRij.setString(2, spelnaam);
            try (ResultSet rs = queryRij.executeQuery()) {
                while (rs.next()) {
                    String combinatie = rs.getString("combinatie");
                    String[] pinnen = combinatie.split("");
                    int[] pinnenInt = new int[pinnen.length];
                    for (int i = 0; i < pinnen.length; i++) {
                        pinnenInt[i] = Integer.parseInt(pinnen[i]);
                    }
                    rijen.add(pinnenInt);
                }
            }

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    int aantalPogingen = rs.getInt("aantalPogingen");
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    switch (moeilijkheidsgraad) {
                        case "MakkelijkSpel":
                            spel = new MakkelijkSpel(rijen.get(rijen.size() - 1));
                            break;
                        case "NormaalSpel":
                            spel = new NormaalSpel(rijen.get(rijen.size() - 1));
                            break;
                        case "MoeilijkSpel":
                            spel = new MoeilijkSpel(rijen.get(rijen.size() - 1));
                    }
                }
            }
            for (int i = 0; i < rijen.size() - 1; i++) {
                spel.getSpelbord().geefPoging(rijen.get(i));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return spel;
    }
    
    public List<String[]> geefLijstUitdagingen(String spelersnaam){
        List<String[]> uitdagingen = new ArrayList<>();        

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_UITDAGINGEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] uitdagingInfo = new String[2];
                    String uitdagingsnaam = rs.getString("spelnaam");
                    uitdagingInfo[0]=uitdagingsnaam;
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    uitdagingInfo[1]=moeilijkheidsgraad;
                    uitdagingen.add(uitdagingInfo);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return uitdagingen;
    }

    
    
    
    
    
    //UITDAGINGMAPPER? DUNNO FOR SURE
    public void voegUitdagingToe(String speler1, String speler2, Spel spel){
        
    }

}
