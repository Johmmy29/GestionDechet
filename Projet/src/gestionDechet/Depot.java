package gestionDechet;

import java.time.LocalDateTime;

public class Depot {

    
    private int compte;
    private TypeDechet type;
    private float quantite;
    private LocalDateTime dateDepot = null;
    private int pointsAttribues;

    public Depot(float quantite) {
    	this.quantite = quantite;
    	this.pointsAttribues = 0;
    }
    
	public int getCompte() {
		return compte;
	}

	public void setCompte(int compte) {
		this.compte = compte;
	}

	public TypeDechet getType() {
		return type;
	}

	public void setType(TypeDechet type) {
		this.type = type;
	}

	public float getQuantite() {
		return quantite;
	}

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}

	public void setDateDepot(LocalDateTime dateDepot) {
		this.dateDepot = dateDepot;
	}

	public void setPointsAttribues(int pointsAttribues) {
	    this.pointsAttribues = pointsAttribues;
	}


    public LocalDateTime getDateDepot() {
        return dateDepot;
    }

    public int getPointsAttribues() {
        return pointsAttribues;
    }

}