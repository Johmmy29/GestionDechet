package gestionDechetDB;

import gestionDechet.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesProduit {

    public Produit create(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (id_produit, nom, prix, id_categorie) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, produit.getIdProduit());
            statement.setString(2, produit.getNom());
            statement.setFloat(3, produit.getPrix());
            statement.setInt(4, produit.getIdCategorie());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return produit;
            } else {
                throw new SQLException("Échec de l'insertion du produit.");
            }
        }
    }

    public Produit findById(int id) throws SQLException {
        String sql = "SELECT * FROM produit WHERE id_produit = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    float prix = rs.getFloat("prix");
                    int idCategorie = rs.getInt("id_categorie");

                    return new Produit(id, nom, prix, idCategorie);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Produit> findAll() throws SQLException {
        String sql = "SELECT * FROM produit";
        List<Produit> produits = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                float prix = rs.getFloat("prix");
                int idCategorie = rs.getInt("id_categorie");

                produits.add(new Produit(id, nom, prix, idCategorie));
            }
        }

        return produits;
    }

    public Produit update(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET nom = ?, prix = ?, id_categorie = ? WHERE id_produit = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produit.getNom());
            statement.setFloat(2, produit.getPrix());
            statement.setInt(3, produit.getIdCategorie());
            statement.setInt(4, produit.getIdProduit());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return produit;
            } else {
                throw new SQLException("Échec de la mise à jour du produit.");
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM produit WHERE id_produit = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
