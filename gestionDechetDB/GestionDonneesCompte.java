/**
 * 
 */
package gestionDechetDB;

import gestionDechet.Compte;
import gestionDechet.Depot;
import gestionDechetDB.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class GestionDonneesCompte {

	/**
	 * @param args
	 */
	 public Compte create(Compte compte) throws SQLException {
	       
		 String sql = "INSERT INTO compte (id, email, mot_de_passe, points_fidelite) VALUES (?, ?, ?, ?)";
	        
	        
	      	try(Connection connection = DatabaseConnection.getConnection();
	                  PreparedStatement statement = connection.prepareStatement(sql)) {
	                    
	                    statement.setInt(1, compte.getId());
	                    statement.setString(2, compte.getEmail());
	                    statement.setString(3, compte.getMotDePasse());
	                    statement.setInt(4, compte.getPointsFidelite());
	                    
	                    int rowsInserted = statement.executeUpdate();
	                    if (rowsInserted > 0) {
	                        return compte;
	                    } else {
	                        throw new SQLException("Échec de la création du compte"); //Quand tu lances 6fois avec le meme compte...
	                    }
	                }
	            }

	        
	            public Compte findById(int id) throws SQLException {
	                String sql = "SELECT * FROM compte WHERE id = ?";
	                
	                try (Connection connection = DatabaseConnection.getConnection();
	                     PreparedStatement statement = connection.prepareStatement(sql)) {
	                    
	                    statement.setInt(1, id);
	                    
	                    try (ResultSet resultSet = statement.executeQuery()) {
	                        if (resultSet.next()) {
	                            String email = resultSet.getString("email");
	                            String motDePasse = resultSet.getString("mot_de_passe");
	                            int pointsFidelite = resultSet.getInt("points_fidelite");
	                            
	                            Compte compte = new Compte(id, email, motDePasse);
	                            compte.setPointsFidelite(pointsFidelite);
	                            
	                            //Ici la méthode pour récupérer les depots avec les comptes => Faire une nouvelle méthode
	                            //Récupérer un objet compte et a partir de celui ci fonction qui récupère une liste
	                            
	                            
	                            return compte;
	                        } else {
	                            return null;
	                        }
	                    }
	                }
	            }

	          
	            public List<Compte> findAll() throws SQLException {
	                String sql = "SELECT * FROM compte";
	                List<Compte> comptes = new ArrayList<>();
	                
	                try (Connection connection = DatabaseConnection.getConnection();
	                     Statement statement = connection.createStatement();
	                     ResultSet resultSet = statement.executeQuery(sql)) {
	                    
	                    while (resultSet.next()) {
	                        int id = resultSet.getInt("id");
	                        String email = resultSet.getString("email");
	                        String motDePasse = resultSet.getString("mot_de_passe");
	                        int pointsFidelite = resultSet.getInt("points_fidelite");
	                        
	                        Compte compte = new Compte(id, email, motDePasse);
	                        compte.setPointsFidelite(pointsFidelite);
	                        
	                        comptes.add(compte);
	                    }
	                    
	                    return comptes;
	                }
	            }

	           
	            public Compte update(Compte compte) throws SQLException {
	                String sql = "UPDATE compte SET email = ?, mot_de_passe = ?, points_fidelite = ? WHERE id = ?";
	                
	                try (Connection connection = DatabaseConnection.getConnection();
	                     PreparedStatement statement = connection.prepareStatement(sql)) {
	                    
	                    statement.setString(1, compte.getEmail());
	                    statement.setString(2, compte.getMotDePasse());
	                    statement.setInt(3, compte.getPointsFidelite());
	                    statement.setInt(4, compte.getId());
	                    
	                    int rowsUpdated = statement.executeUpdate();
	                    if (rowsUpdated > 0) {
	                        return compte;
	                    } else {
	                        throw new SQLException("Échec de la mise à jour du compte");
	                    }
	                }
	            }

	            

	        

}
