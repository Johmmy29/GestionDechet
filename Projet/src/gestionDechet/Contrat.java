package gestionDechet;


import java.time.LocalDateTime;

public class Contrat {
	
	private int idContrat;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String reglesUtilisation;
    private int idCommerce;

    public Contrat(int idContrat, int idCommerce, LocalDateTime debut, LocalDateTime fin, String regles) {
    	this.idContrat = idContrat;
    	this.dateDebut = debut; 
    	this.dateFin = fin; 
    	this.reglesUtilisation = regles;
    }

    public int getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public String getReglesUtilisation() {
		return reglesUtilisation;
	}
	
    public String getConditionUtilisation() {
        return reglesUtilisation;
    }

    public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public void setReglesUtilisation(String reglesUtilisation) {
        this.reglesUtilisation = reglesUtilisation;
    }
    
    public int getIdCommerce() {
        return idCommerce;
    }
    
    public void setIdCommerce(int idCommerce) {
        this.idCommerce = idCommerce;
    }
    
    public boolean renouvelerContrat(LocalDateTime nouvelleDateFin) {
        if (nouvelleDateFin.isAfter(this.dateFin)) {
            this.dateFin = nouvelleDateFin;
            System.out.println("\nLa date a bien été modifiée !");
            return true;
        }
        System.out.println("\nLa nouvelle date n'est pas valide !");
        return false;
    }
 
    public boolean estActif() {
    	LocalDateTime maintenant = LocalDateTime.now();
    	return (dateDebut.isBefore(maintenant) || dateDebut.isEqual(maintenant)) && dateFin.isAfter(maintenant);
    }   
}