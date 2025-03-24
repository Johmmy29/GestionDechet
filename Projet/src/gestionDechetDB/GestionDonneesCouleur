package gestionDechetDB;

import gestionDechet.Couleur;
import java.sql.*;

public class GestionDonneesCouleur {

    public Couleur findById(int id) throws SQLException {
        String sql = "SELECT * FROM couleur WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Couleur.valueOf(rs.getString("couleur"));
                } else {
                    return null;
                }
            }
        }
    }

    // Add further methods as needed for managing colors
}
