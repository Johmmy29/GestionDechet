package gestionDechetDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	
	//Pour avoir les variables partout
	
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    public static final String USER = "root"; 
    public static final String PASSWORD = "cytech0001"; 
    
    private static Connection connection = null;
    
    private DatabaseConnection() {
    	//Constructeur vide
    }
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed())  { //On verifie que la connexion existe pas
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL non trouvé", e);
            } catch (SQLException e) {
                throw new SQLException("Erreur de connexion à la base de données", e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() { //necessaire pour plusieurs tests a la suite
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}


