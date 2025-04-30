package gestionDechetDB;

import gestionDechet.TypeDechet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesTypeDechet {

    public TypeDechet findByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM type_dechet WHERE nom = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return TypeDechet.valueOf(rs.getString("nom"));
                } else {
                    return null;
                }
            }
        }
    }

    public List<TypeDechet> findAll() throws SQLException {
        String sql = "SELECT * FROM type_dechet";
        List<TypeDechet> types = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                types.add(TypeDechet.valueOf(rs.getString("nom")));
            }
        }

        return types;
    }
}
