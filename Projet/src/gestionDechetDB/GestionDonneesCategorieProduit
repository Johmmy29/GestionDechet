package gestionDechetDB;

import gestionDechet.CategorieProduit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesCategorieProduit {

    public CategorieProduit create(CategorieProduit categorie) throws SQLException {
        String sql = "INSERT INTO categorie_produit (id_categorie, nom, points_necessaires, taux_reduction) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categorie.getIdCategorie());
            statement.setString(2, categorie.getNom());
            statement.setInt(3, categorie.getPointsNecessaires());
            statement.setFloat(4, categorie.getTauxReduction());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return categorie;
            } else {
                throw new SQLException("Échec de l'insertion de la catégorie.");
            }
        }
    }

    public CategorieProduit findById(int id) throws SQLException {
        String sql = "SELECT * FROM categorie_produit WHERE id_categorie = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    int pointsNecessaires = rs.getInt("points_necessaires");
                    float tauxReduction = rs.getFloat("taux_reduction");

                    return new CategorieProduit(id, nom, pointsNecessaires, tauxReduction);
                } else {
                    return null;
                }
            }
        }
    }

    public List<CategorieProduit> findAll() throws SQLException {
        String sql = "SELECT * FROM categorie_produit";
        List<CategorieProduit> categories = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_categorie");
                String nom = rs.getString("nom");
                int pointsNecessaires = rs.getInt("points_necessaires");
                float tauxReduction = rs.getFloat("taux_reduction");

                categories.add(new CategorieProduit(id, nom, pointsNecessaires, tauxReduction));
            }
        }

        return categories;
    }

    public CategorieProduit update(CategorieProduit categorie) throws SQLException {
        String sql = "UPDATE categorie_produit SET nom = ?, points_necessaires = ?, taux_reduction = ? WHERE id_categorie = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categorie.getNom());
            statement.setInt(2, categorie.getPointsNecessaires());
            statement.setFloat(3, categorie.getTauxReduction());
            statement.setInt(4, categorie.getIdCategorie());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return categorie;
            } else {
                throw new SQLException("Échec de la mise à jour de la catégorie.");
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM categorie_produit WHERE id_categorie = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
