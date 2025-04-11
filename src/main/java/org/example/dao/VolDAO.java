package org.example.dao;

import org.example.Vol;
import org.example.db.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VolDAO {

    public static void insert(Vol vol) throws SQLException {
        String sql = "INSERT INTO vol (numero_vol, origine, destination, date_depart, date_arrivee, etat) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vol.getNumeroVol());
            ps.setString(2, vol.getPrimaryOrigin());
            ps.setString(3, vol.getPrimaryDestination());
            ps.setTimestamp(4, new Timestamp(vol.getDateHeureDepart().getTime()));
            ps.setTimestamp(5, new Timestamp(vol.getDateHeureArrivee().getTime()));
            ps.setString(6, vol.getEtat());

            ps.executeUpdate();
        }
    }

    public static List<Vol> findAll() throws SQLException {
        List<Vol> result = new ArrayList<>();
        String sql = "SELECT numero_vol, origine, destination, date_depart, date_arrivee, etat FROM vol";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String numeroVol = rs.getString("numero_vol");
                String origine = rs.getString("origine");
                String destination = rs.getString("destination");
                Timestamp dateDepart = rs.getTimestamp("date_depart");
                Timestamp dateArrivee = rs.getTimestamp("date_arrivee");
                String etat = rs.getString("etat");

                Vol vol = new Vol(numeroVol, origine, destination,
                        new Date(dateDepart.getTime()),
                        new Date(dateArrivee.getTime()));
                vol.modifierVol(origine, destination, new Date(dateDepart.getTime()), new Date(dateArrivee.getTime()), etat);

                result.add(vol);
            }
        }

        return result;
    }
}
