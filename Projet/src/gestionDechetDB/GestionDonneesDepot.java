package gestionDechetDB;

import gestionDechet.Depot;
import gestionDechet.TypeDechet;
import gestionDechetDB.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionDonneesDepot {//implements compte<DAO>  (meilleure méthode ?)

    
    public Depot create(Depot depot) throws SQLException {
        String sql = "INSERT INTO depot (compte_id, type_dechet, quantite, date_depot, points_attribues) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) { // On peut ajouter après Statement.RETURN_GENERATED_KEYS) Permet de recupere l'ID du depot
            
            statement.setInt(1, depot.getCompte()); //Les infos classiques
            statement.setString(2, depot.getType().toString());
            statement.setFloat(3, depot.getQuantite());
            
            if (depot.getDateDepot() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(depot.getDateDepot()));
            } else {
                statement.setNull(4, Types.TIMESTAMP); //Catch error
               
            }
            
            statement.setInt(5, depot.getPointsAttribues());
            
            

                
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) { //Verifie au'il y a bien eu une insertion
                return depot;
            } else {
                throw new SQLException("Échec de la création du dépôt");
            }
            
        }
    }

    
    public Depot findById(int id) throws SQLException {
        String sql = "SELECT * FROM depot WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { //S'il y a en a un
                	
                    float quantite = resultSet.getFloat("quantite");
                    int compteId = resultSet.getInt("compte_id");
                    TypeDechet type = TypeDechet.valueOf(resultSet.getString("type_dechet"));
                    
                    Timestamp dateDepotTimestamp = resultSet.getTimestamp("date_depot");
                    LocalDateTime dateDepot = dateDepotTimestamp != null ? dateDepotTimestamp.toLocalDateTime() : null;
                    
                    int pointsAttribues = resultSet.getInt("points_attribues");
                    
                    Depot depot = new Depot(quantite);
                    depot.setCompte(compteId);
                    depot.setType(type);
                    depot.setDateDepot(dateDepot);
                    depot.setPointsAttribues(pointsAttribues);
                    
                    return depot;
                } else {
                    return null;
                }
            }
        }
    }

  
    public List<Depot> findAll() throws SQLException {
        String sql = "SELECT * FROM depot";
        List<Depot> depots = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                float quantite = resultSet.getFloat("quantite");
                int compteId = resultSet.getInt("compte_id");
                TypeDechet type = TypeDechet.valueOf(resultSet.getString("type_dechet"));
                
                Timestamp dateDepotTimestamp = resultSet.getTimestamp("date_depot");
                LocalDateTime dateDepot = dateDepotTimestamp != null ? dateDepotTimestamp.toLocalDateTime() : null;
                
                int pointsAttribues = resultSet.getInt("points_attribues");
                
                Depot depot = new Depot(quantite);
                depot.setCompte(compteId);
                depot.setType(type);
                depot.setDateDepot(dateDepot);
                depot.setPointsAttribues(pointsAttribues);
                
                depots.add(depot);
            }
            
            return depots;
        }
    }

    
    public Depot update(Depot depot) throws SQLException {
        String sql = "UPDATE depot SET compte_id = ?, type_dechet = ?, quantite = ?, date_depot = ?, points_attribues = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, depot.getCompte());
            statement.setString(2, depot.getType().toString());
            statement.setFloat(3, depot.getQuantite());
            
            if (depot.getDateDepot() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(depot.getDateDepot()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }
            
            statement.setInt(5, depot.getPointsAttribues());
            // Note: Il manque un ID dans la classe Depot pour cette méthode.
            // Vous devrez peut-être ajouter un attribut id à votre classe Depot.
            // Pour l'exemple, je mets simplement 0
            statement.setInt(6, 0);
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return depot;
            } else {
                throw new SQLException("Échec de la mise à jour du dépôt");
            }
        }
    }

   
    public boolean delete(Depot depot) throws SQLException {
        // Note: Il manque un ID dans la classe Depot pour cette méthode.
        // Vous devrez peut-être ajouter un attribut id à votre classe Depot.
        String sql = "DELETE FROM depot WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Pour l'exemple, je mets simplement 0
            statement.setInt(1, 0);
            
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
    
    // Méthode pour trouver tous les dépôts d'un compte spécifique
    public List<Depot> findByCompteId(int compteId) throws SQLException {
        String sql = "SELECT * FROM depot WHERE compte_id = ?";
        List<Depot> depots = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, compteId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    float quantite = resultSet.getFloat("quantite");
                    TypeDechet type = TypeDechet.valueOf(resultSet.getString("type_dechet"));
                    
                    Timestamp dateDepotTimestamp = resultSet.getTimestamp("date_depot");
                    LocalDateTime dateDepot = dateDepotTimestamp != null ? dateDepotTimestamp.toLocalDateTime() : null;
                    
                    int pointsAttribues = resultSet.getInt("points_attribues");
                    
                    Depot depot = new Depot(quantite);
                    depot.setCompte(compteId);
                    depot.setType(type);
                    depot.setDateDepot(dateDepot);
                    depot.setPointsAttribues(pointsAttribues);
                    
                    depots.add(depot);
                }
            }
            
            return depots;
        }
    }
}