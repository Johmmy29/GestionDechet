package gestionDechetDB;

import gestionDechet.Poubelle;
import gestionDechet.Couleur;
import java.sql.*;

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
                    float capaciteMax = rs.getFloat("capacite_max");

                    return new Poubelle(id, idEmplacement, couleur, capaciteActuelle, capaciteMax);
                } else {
                    return null;
                }
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
