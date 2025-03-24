package gestionDechetDB;

import java.sql.*;
import java.time.LocalDateTime;
import gestionDechet.Contrat;
import gestionDechet.Commerce;

public class CentreDeTriDAO {

    // Associer un contrat à un commerce
    public static boolean creerContrat(Contrat contrat) throws SQLException {
        String sql = "INSERT INTO contrat (id_contrat, id_commerce, date_debut, date_fin, regles_utilisation) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrat.getIdContrat());
            stmt.setInt(2, contrat.getIdCommerce());
            stmt.setTimestamp(3, Timestamp.valueOf(contrat.getDateDebut()));
            stmt.setTimestamp(4, Timestamp.valueOf(contrat.getDateFin()));
            stmt.setString(5, contrat.getReglesUtilisation());

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean creerCommerce(Commerce commerce) throws SQLException {
        String sql = "INSERT INTO commerce (id_commerce, nom, adresse, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, commerce.getIdCommerce());
            stmt.setString(2, commerce.getNom());
            stmt.setString(3, commerce.getAdresse());
            stmt.setString(4, commerce.getType());

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean renouvelerContrat(int idContrat, LocalDateTime nouvelleDateFin) throws SQLException {
        String sql = "UPDATE contrat SET date_fin = ? WHERE id_contrat = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(nouvelleDateFin));
            stmt.setInt(2, idContrat);

            return stmt.executeUpdate() > 0;
        }
    }
}
