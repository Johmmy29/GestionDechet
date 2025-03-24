package gestionDechetDB;

import gestionDechet.Couleur;
import gestionDechet.Poubelle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesPoubelle {

    public Poubelle create(Poubelle poubelle) throws SQLException {
        String sql = "INSERT INTO poubelle (id_poubelle, id_emplacement, couleur, capacite_actuelle, capacite_max) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, poubelle.getIdPoubelle());
            statement.setInt(2, poubelle.getIdEmplacement());
            statement.setString(3, poubelle.getCouleur().toString());
            statement.setFloat(4, poubelle.getCapaciteActuelle());
            statement.setFloat(5, poubelle.getCapaciteMax());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return poubelle;
            } else {
                throw new SQLException("Échec de l'insertion de la poubelle.");
            }
        }
    }

    public Poubelle findById(int id) throws SQLException {
        String sql = "SELECT * FROM poubelle WHERE id_poubelle = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int idEmplacement = rs.getInt("id_emplacement");
                    Couleur couleur = Couleur.valueOf(rs.getString("couleur"));
                    float capaciteActuelle = rs.getFloat("capacite_actuelle");

                    Poubelle poubelle = new Poubelle(id, idEmplacement, couleur);
                    poubelle.setCapaciteActuelle(capaciteActuelle);

                    return poubelle;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Poubelle> findAll() throws SQLException {
        String sql = "SELECT * FROM poubelle";
        List<Poubelle> poubelles = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_poubelle");
                int idEmplacement = rs.getInt("id_emplacement");
                Couleur couleur = Couleur.valueOf(rs.getString("couleur"));
                float capaciteActuelle = rs.getFloat("capacite_actuelle");

                Poubelle poubelle = new Poubelle(id, idEmplacement, couleur);
                poubelle.setCapaciteActuelle(capaciteActuelle);

                poubelles.add(poubelle);
            }
        }

        return poubelles;
    }

    public Poubelle update(Poubelle poubelle) throws SQLException {
        String sql = "UPDATE poubelle SET id_emplacement = ?, couleur = ?, capacite_actuelle = ? WHERE id_poubelle = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, poubelle.getIdEmplacement());
            statement.setString(2, poubelle.getCouleur().toString());
            statement.setFloat(3, poubelle.getCapaciteActuelle());
            statement.setInt(4, poubelle.getIdPoubelle());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return poubelle;
            } else {
                throw new SQLException("Échec de la mise à jour de la poubelle.");
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM poubelle WHERE id_poubelle = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}

