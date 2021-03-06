package persistentie;

import domein.MakkelijkSpel;
import domein.MoeilijkSpel;
import domein.NormaalSpel;
import domein.Spel;
import exceptions.SpelnaamNietUniekException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Zorgt voor de connectie met de Spel objecten uit de databank.
 *
 */
public class SpelMapper {

    private static final String INSERT_SPEL = "INSERT INTO ID222177_g68.Spel (spelnaam, spelersnaam, aantalPogingen, moeilijkheidsgraad, uitdagingsId) VALUES (?,?,?,?,?)";
    private static final String INSERT_RIJ = "INSERT INTO ID222177_g68.Rij (rijNummer, spelnaam, spelersnaam, combinatie) VALUES (?,?,?,?)";
    private static final String GEEF_SPELLEN = "SELECT spelnaam, moeilijkheidsgraad FROM ID222177_g68.Spel where spelersnaam = ?";
    private static final String GEEF_SPEL = "SELECT * FROM ID222177_g68.Spel WHERE spelersnaam = ? AND spelnaam = ?";
    private static final String GEEF_RIJEN = "SELECT * FROM ID222177_g68.Rij WHERE spelersnaam = ? AND spelnaam = ?";
    private static final String VERWIJDER_SPEL = "DELETE FROM ID222177_g68.Spel WHERE spelnaam = ? AND spelersnaam = ?";
    //private static final String UPDATE_SPEL = "UPDATE ID222177_g68.Spel SET isUitdaging = 1 WHERE spelnaam = ? AND spelersnaam = ?";
    //private static final String GEEF_UITDAGINGEN = "SELECT spelnaam, moeilijkheidsgraad FROM ID222177_g68.Spel WHERE spelersnaam = ? AND tegenspeler is not null";
    //SQL statement opvragen aantalPogingen per speler per uitdaging (zal gebruikt worden om score te berekenen)    

    /**
     * Methode die een spel zal toevoegen aan de database met de juiste spelnaam bij de juiste speler.
     * 
     * @param spelnaam de naam van het spel.
     * @param spelersnaam de naam van de speler.
     * @param spel het spel object.
     */
    public void voegSpelToe(String spelnaam, String spelersnaam, Spel spel) {               //moet nog aangepast worden//EDIT: DONE        
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement querySpel = conn.prepareStatement(INSERT_SPEL);
                PreparedStatement queryRij = conn.prepareStatement(INSERT_RIJ)) {
            querySpel.setString(1, spelnaam);
            querySpel.setString(2, spelersnaam);
            querySpel.setInt(3, spel.getSpelbord().getAantalPogingen());
            querySpel.setString(4, spel.getClass().getSimpleName());
            if (spel.getId() == 0) {
                querySpel.setNull(5, Types.INTEGER);
            } else {
                querySpel.setInt(5, spel.getId());
            }            
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
            //throw new RuntimeException(ex);
            throw new SpelnaamNietUniekException();
        }
    }

//    public void spelIsUitdaging(String spelnaam, String spelersnaam) {
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement(UPDATE_SPEL)) {
//            query.setString(1, spelnaam);
//            query.setString(2, spelersnaam);
//            query.executeUpdate();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
    /**
     * Methode die de alle spelnamen en moeilijkheidsgraden van de opgeslagen spellen van een bepaalde
     * speler zal weergeven.
     * 
     * @param spelersnaam naam van de speler.
     * @return een list met String[] die de spelnamen en moeilijkheidsgraden van de spellen bevat.
     */
    public List<String[]> geefSpelnamen(String spelersnaam) {
        List<String[]> spelnamen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPELLEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] spelInfo = new String[2];
                    String spelnaam = rs.getString("spelnaam");
                    spelInfo[0] = spelnaam;
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    spelInfo[1] = moeilijkheidsgraad;
                    spelnamen.add(spelInfo);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelnamen;
    }

    /**
     * Methode die het spel met een bepaalde spelnaam van een bepaalde speler
     * zal verwijderen.
     * 
     * @param spelnaam de naam van het spel.
     * @param spelersnaam de naam van de speler.
     */
    public void verwijderSpel(String spelnaam, String spelersnaam) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement spelQuery = conn.prepareStatement(VERWIJDER_SPEL)) {
            spelQuery.setString(1, spelnaam);
            spelQuery.setString(2, spelersnaam);
            spelQuery.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Methode die het het spel met een bepaalde spelnaam van een bepaalde speler zal laden.
     * 
     * @param spelnaam de naam van het spel.
     * @param spelersnaam de naam van de speler.
     * 
     * @return het opgevraagde Spel object.
     */
    public Spel laadSpel(String spelnaam, String spelersnaam) {
        Spel spel = null;
        List<int[]> rijen = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_SPEL);
                PreparedStatement queryRij = conn.prepareStatement(GEEF_RIJEN)) {
            query.setString(1, spelersnaam);
            query.setString(2, spelnaam);
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
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    int id = rs.getInt("uitdagingsId");
                    switch (moeilijkheidsgraad) {
                        case "MakkelijkSpel":
                            spel = new MakkelijkSpel(rijen.get(rijen.size() - 1), id);
                            break;
                        case "NormaalSpel":
                            spel = new NormaalSpel(rijen.get(rijen.size() - 1), id);
                            break;
                        case "MoeilijkSpel":
                            spel = new MoeilijkSpel(rijen.get(rijen.size() - 1), id);
                    }
                    for (int i = 0; i < rijen.size() - 1; i++) {
                        spel.getSpelbord().geefPoging(rijen.get(i));
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spel;
    }

}
