package gestionDechetDB;

import gestionDechet.Contrat;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesContrat {

    public Contrat create(Contrat contrat) throws SQLException {
        String sql = "INSERT INTO contrat (id_contrat, id_commerce, date_debut, date_fin, regles_utilisation) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contrat.getIdContrat());
            statement.setInt(2, contrat.getIdCommerce());
            statement.setTimestamp(3, Timestamp.valueOf(contrat.getDateDebut()));
            statement.setTimestamp(4, Timestamp.valueOf(contrat.getDateFin()));
            statement.setString(5, contrat.getReglesUtilisation());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return contrat;
            } else {
                throw new SQLException("Échec de la création du contrat.");
            }
        }
    }

    public Contrat findById(int id) throws SQLException {
        String sql = "SELECT * FROM contrat WHERE id_contrat = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int idCommerce = rs.getInt("id_commerce");
                    LocalDateTime dateDebut = rs.getTimestamp("date_debut").toLocalDateTime();
                    LocalDateTime dateFin = rs.getTimestamp("date_fin").toLocalDateTime();
                    String regles = rs.getString("regles_utilisation");

                    return new Contrat(id, idCommerce, dateDebut, dateFin, regles);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Contrat> findAll() throws SQLException {
        String sql = "SELECT * FROM contrat";
        List<Contrat> contrats = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int idContrat = rs.getInt("id_contrat");
                int idCommerce = rs.getInt("id_commerce");
                LocalDateTime dateDebut = rs.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime dateFin = rs.getTimestamp("date_fin").toLocalDateTime();
                String regles = rs.getString("regles_utilisation");

                Contrat contrat = new Contrat(idContrat, idCommerce, dateDebut, dateFin, regles);
                contrats.add(contrat);
            }
        }

        return contrats;
    }

    public Contrat update(Contrat contrat) throws SQLException {
        String sql = "UPDATE contrat SET id_commerce = ?, date_debut = ?, date_fin = ?, regles_utilisation = ? WHERE id_contrat = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contrat.getIdCommerce());
            statement.setTimestamp(2, Timestamp.valueOf(contrat.getDateDebut()));
            statement.setTimestamp(3, Timestamp.valueOf(contrat.getDateFin()));
            statement.setString(4, contrat.getReglesUtilisation());
            statement.setInt(5, contrat.getIdContrat());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return contrat;
            } else {
                throw new SQLException("Échec de la mise à jour du contrat.");
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM contrat WHERE id_contrat = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}

