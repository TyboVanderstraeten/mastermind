/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.MakkelijkSpel;
import domein.MoeilijkSpel;
import domein.NormaalSpel;
import domein.Spel;
import domein.Uitdaging;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bramd
 */
public class UitdagingMapper {

    private static final String INSERT_UITDAGING = "INSERT INTO ID222177_g68.Uitdaging (speler1, speler2, moeilijkheidsgraad, code) VALUES (?,?,?,?)";
    private static final String GEEF_UITDAGINGEN = "SELECT speler1, moeilijkheidsgraad FROM ID222177_g68.Uitdaging WHERE speler2 = ? AND isAanvaard = 0";
    private static final String GEEF_UITDAGING = "SELECT speler1, speler2, moeilijkheidsgraad, code, id FROM ID222177_g68.Uitdaging WHERE speler1 = ? AND isAanvaard = 0 AND aantalPogingenS2 = 0";
    private static final String GEEF_UITDAGINGINFO = "SELECT speler1, speler2, aantalPogingenS1, aantalPogingenS2 FROM ID222177_g68.Uitdaging WHERE id = ?";
//    private static final String GEEF_AANTALPOGINGEN = "SELECT speler1, aantalPogingenS1, aantalPogingenS2 FROM ID222177_g68.Uitdaging WHERE id = ?";
    private static final String UPDATE_AANTALPOGINGENS1 = "UPDATE ID222177_g68.Uitdaging SET aantalPogingenS1 = ? WHERE id = ?";
    private static final String UPDATE_AANTALPOGINGENS2 = "UPDATE ID222177_g68.Uitdaging SET aantalPogingenS2 = ? WHERE id = ?";

    //NEW
    private static final String GEEF_AANVAARDE_UITDAGINGEN = "SELECT speler1, moeilijkheidsgraad FROM ID222177_g68.Uitdaging WHERE (speler1 = ? AND aantalPogingenS1 = 0) OR (speler2 =? AND isAanvaard = 1 AND aantalPogingenS2 = 0)";
    private static final String AANVAARD_UITDAGING = "UPDATE ID222177_g68.Uitdaging set isAanvaard = 1 where id = ?";

    public void registreerUitdaging(String spelersnaam1, String spelersnaam2, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_UITDAGING)) {
            query.setString(1, spelersnaam1);
            query.setString(2, spelersnaam2);
            query.setString(3, spel.getClass().getSimpleName());
            query.setString(4, Arrays.toString(Arrays.copyOfRange(spel.getSpelbord().getRijen()[spel.getSpelbord().getRijen().length - 1].geefPinkleuren(), 0, spel.getClass().getSimpleName().equals("MoeilijkSpel") ? 5 : 4)).replaceAll("[^0-9]", ""));//.replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", ""));
            query.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public List<String[]> geefLijstUitdagingen(String spelersnaam) {
        List<String[]> uitdagingen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_UITDAGINGEN)) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String[] uitdagingInfo = new String[2];
                    String uitdagingsnaam = rs.getString("speler1");
                    uitdagingInfo[0] = uitdagingsnaam;
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    uitdagingInfo[1] = moeilijkheidsgraad;
                    uitdagingen.add(uitdagingInfo);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return uitdagingen;
    }

    public Spel laadUitdaging(String uitdager, String spelersnaam) {

        Spel spel = null;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_UITDAGING)) {
            query.setString(1, uitdager);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    String[] codeString = rs.getString("code").split("");
                    int id = rs.getInt("id");
//                    String speler1 = rs.getString("speler1");
//                    String speler2 = rs.getString("speler2");       

                    int[] code = new int[codeString.length];
                    for (int i = 0; i < codeString.length; i++) {
                        code[i] = Integer.parseInt(codeString[i]);
                    }                    
                    switch (moeilijkheidsgraad) {
                        case "MakkelijkSpel":
                            spel = new MakkelijkSpel(code, id);
                            break;
                        case "NormaalSpel":
                            spel = new NormaalSpel(code, id);
                            break;
                        case "MoeilijkSpel":
                            spel = new MoeilijkSpel(code, id);
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spel;

    }

//    public int geefAantalPogingen(int id, String spelersnaam) {
//        int aantalPogingenS1, aantalPogingenS2, uitvoer = 0;
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement(GEEF_AANTALPOGINGEN)) {
//            query.setInt(1, id);
//            try (ResultSet rs = query.executeQuery()) {
//                if (rs.next()) {
//                    aantalPogingenS1 = rs.getInt("aantalPogingenS1");
//                    aantalPogingenS2 = rs.getInt("aantalPogingenS2");
//                    String speler1 = rs.getString("speler1");
//                    if (speler1.equals(spelersnaam)) {
//                        uitvoer = aantalPogingenS2;
//                    } else {
//                        uitvoer = aantalPogingenS1;
//                    }
//
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return uitvoer;
//
//    }
    
    public String[] geefUitdagingInfo(int id, String spelersnaam) {
        int aantalPogingenS1, aantalPogingenS2;
        String[] uitdagingInfo = new String[3];                     // { speler1, speler2, aantalPogingenTegenspeler }
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_UITDAGINGINFO)) {
            query.setInt(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String speler1 = rs.getString("speler1");
                    String speler2 = rs.getString("speler2");
                    aantalPogingenS1 = rs.getInt("aantalPogingenS1");
                    aantalPogingenS2 = rs.getInt("aantalPogingenS2");    
                    uitdagingInfo[0] = speler1;
                    uitdagingInfo[1] = speler2;
                    
                    if (speler1.equals(spelersnaam)) {
                        uitdagingInfo[2] = Integer.toString(aantalPogingenS2);
                    } else {
                        uitdagingInfo[2] = Integer.toString(aantalPogingenS1);
                    }

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return uitdagingInfo;

    }
    
    
    

    public void voegAantalPogingenToeS1(int aantalPogingen, int id) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALPOGINGENS1)) {

            query.setInt(1, aantalPogingen);
            query.setInt(2, id);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void voegAantalPogingenToeS2(int aantalPogingen, int id) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_AANTALPOGINGENS2)) {
            query.setInt(1, aantalPogingen);
            query.setInt(2, id);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String geefOpenUitdaging(String spelersnaam) {
        String naam = null;
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AANVAARDE_UITDAGINGEN)) {
            query.setString(1, spelersnaam);
            query.setString(2, spelersnaam);
            query.executeQuery();
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    naam = rs.getString("speler1");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return naam;
    }

//    public void updateIsHuidigeSpeler1(String spelersnaam){
//        try (
//                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement(UPDATE_ISHUIDIGESPELER1)) {            
//            query.setString(1, spelersnaam);
//            query.executeUpdate();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//    
    public void aanvaardUitdaging(int id) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(AANVAARD_UITDAGING)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
