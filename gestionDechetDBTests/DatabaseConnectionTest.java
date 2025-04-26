
package gestionDechetDBTests;


import java.sql.Connection;
import java.sql.SQLException;

import gestionDechetDB.DatabaseConnection;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        testConnection();
    }
    
    public static void testConnection() {
        System.out.println("Test de connexion à la base de données...");
        
        try {
            // Tentative de connexion
            Connection conn = DatabaseConnection.getConnection();
            
            // Si nous arrivons ici, la connexion est établie
            System.out.println("Connexion établie avec succès !");
            
            // Fermeture de la connexion
            DatabaseConnection.closeConnection();
            System.out.println("Connexion fermée.");
            
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données !");
            System.err.println("Message d'erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
