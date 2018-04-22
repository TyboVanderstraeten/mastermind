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
    private static final String GEEF_UITDAGINGEN = "SELECT speler1, moeilijkheidsgraad FROM ID222177_g68.Uitdaging WHERE speler2 = ?";
    private static final String GEEF_UITDAGING = "SELECT moeilijkheidsgraad, code FROM ID222177_g68.Uitdaging WHERE speler1 = ?";

    public void registreerUitdaging(String spelersnaam1, String spelersnaam2, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_UITDAGING)) {
            query.setString(1, spelersnaam1);
            query.setString(2, spelersnaam2);
            query.setString(3, spel.getClass().getSimpleName());
            query.setString(4, Arrays.toString(Arrays.copyOfRange(spel.getSpelbord().getRijen()[spel.getSpelbord().getRijen().length - 1].geefPinkleuren(), 0, spel.getClass().getSimpleName().equals("MoeilijkSpel") ? 5 : 4)).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", ""));
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

    public Spel laadUitdaging(String spelersnaam) {

        Spel spel = null;
        List<int[]> rijen = new ArrayList<>();
        String niveau = "";
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_UITDAGING)) {
            query.setString(1, spelersnaam);

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String moeilijkheidsgraad = rs.getString("moeilijkheidsgraad");
                    String[] codeString = rs.getString("code").split("");
                    int[] code = new int[codeString.length];
                    for (int i = 0; i < codeString.length; i++) {
                        code[i] = Integer.parseInt(codeString[i]);
                    }
                    switch (moeilijkheidsgraad) {
                        case "MakkelijkSpel":
                            spel = new MakkelijkSpel(code);
                            break;
                        case "NormaalSpel":
                            spel = new NormaalSpel(code);
                            break;
                        case "MoeilijkSpel":
                            spel = new MoeilijkSpel(code);
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
