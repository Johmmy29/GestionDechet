package gestionDechetDB;

import gestionDechet.Commerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesCommerce {

    public Commerce create(Commerce commerce) throws SQLException {
        String sql = "INSERT INTO commerce (id_commerce, nom, adresse, type) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, commerce.getIdCommerce());
            statement.setString(2, commerce.getNom());
            statement.setString(3, commerce.getAdresse());
            statement.setString(4, commerce.getType());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return commerce;
            } else {
                throw new SQLException("Échec de l'insertion du commerce.");
            }
        }
    }

    public Commerce findById(int id) throws SQLException {
        String sql = "SELECT * FROM commerce WHERE id_commerce = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String adresse = rs.getString("adresse");
                    String type = rs.getString("type");

                    return new Commerce(id, nom, adresse, type);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Commerce> findAll() throws SQLException {
        String sql = "SELECT * FROM commerce";
        List<Commerce> commerces = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_commerce");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String type = rs.getString("type");

                commerces.add(new Commerce(id, nom, adresse, type));
            }
        }

        return commerces;
    }

    public Commerce update(Commerce commerce) throws SQLException {
        String sql = "UPDATE commerce SET nom = ?, adresse = ?, type = ? WHERE id_commerce = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, commerce.getNom());
            statement.setString(2, commerce.getAdresse());
            statement.setString(3, commerce.getType());
            statement.setInt(4, commerce.getIdCommerce());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return commerce;
            } else {
                throw new SQLException("Échec de la mise à jour du commerce.");
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM commerce WHERE id_commerce = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}

