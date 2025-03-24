package gestionDechet;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class Compte {

    private int id;
    private String email;
    private String motDePasse;
    private int pointsFidelite;
    private List<Depot> depots;

    

    public Compte(int id, String email, String motDePasse) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.pointsFidelite = 0;
        this.depots = new ArrayList<>();

        for (TypeDechet type : TypeDechet.values()) {
            Depot depot = new Depot(0f);
            depot.setType(type);
            depot.setCompte(this.id); 
            depots.add(depot);
        }

    }
  
    public int getId() {
        return id;
    }

    public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
        return email;
    }

    public int getPointsFidelite() {
        return pointsFidelite;
    }
    

    public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }
    
    public void consulterPoints() {
        System.out.println("\n Votre nombre de points est de : " + pointsFidelite);
     }
    
    public void consulterHistoriqueDepots() {
        List<Depot> tousLesDepots = CentreDeTri.INSTANCE.getHistoriqueDepots();
        boolean vide = true;

        System.out.println("\nDépôts effectués par le compte ID : " + id);

        for (Depot d : tousLesDepots) {
            if (d.getCompte() == this.id) {
                System.out.println("- Type : " + d.getType() +
                                   ", Quantité : " + d.getQuantite() + " kg");
                vide = false;
            }
        }

        if (vide) {
            System.out.println("Aucun dépôt trouvé pour ce compte.");
        }
    }


    public boolean utiliserPoints(CategorieProduit categorie, int idProduit) {
        if (pointsFidelite < categorie.getPointsNecessaires()) {
            System.out.println("\nVous n'avez pas assez de points pour cette réduction.");
            return false;
        }
        for (Produit produit : categorie.getProduits()) {
            if (produit.getIdProduit() == idProduit) {
                float prix = produit.getPrix();
                float prixReduit = categorie.appliquerReduction(prix, pointsFidelite);

                System.out.println("\nVous achetez le produit \"" + produit.getNom() + "\" avec une réduction de " + (prix - prixReduit) + ". Le prix passe donc de " + prix + " € à " + prixReduit + " €");
           
                pointsFidelite -= categorie.getPointsNecessaires();
                return true;
            }
        }

        System.out.println("\nAucun produit trouvé avec l'ID : " + idProduit);
        return false;
    }

    public Couleur couleurPoubelle(Depot depot) {
        TypeDechet type = depot.getType();
        switch (type) {
            case VERRE:
                return Couleur.VERT;
            case EMBALLAGE:
            case CARTON:
            case PLASTIQUE:
            case CANETTE:
            case CONSERVE:
                return Couleur.JAUNE;
            case PAPIER:
                return Couleur.BLEUE;
            case AUTRE:
            default:
                return Couleur.NOIR;
        }
    }

    public void viderDepot(int idPoubelle, TypeDechet typeDechet) {
        CentreDeTri centre = CentreDeTri.INSTANCE;
        Depot depot = null;
        for (Depot d : depots) {
            if (d.getType() == typeDechet) {
                depot = d;
                break;
            }
        }

        if (depot.getQuantite() <= 0) {
            System.out.println("\nLe dépôt de type " + typeDechet + " est vide.");
            return;
        }

        Poubelle cible = null;
        for (Poubelle p : centre.getPoubelles()) {
            if (p.getIdPoubelle() == idPoubelle) {
                cible = p;
                break;
            }
        }

        if (cible == null) {
            System.out.println("\nAucune poubelle trouvée avec l'ID : " + idPoubelle);
            return;
        }

        float espaceDispo = cible.getCapaciteMax() - cible.getCapaciteActuelle();
        float quantiteAVider = Math.min(depot.getQuantite(), espaceDispo);
        cible.setCapaciteActuelle(cible.getCapaciteActuelle() + quantiteAVider);
        if (cible.getCapaciteActuelle() == cible.getCapaciteMax()) {
            cible.estPleine();
        }

        depot.setDateDepot(LocalDateTime.now());

        Couleur couleurAttendue;
        switch (typeDechet) {
            case VERRE:
                couleurAttendue = Couleur.VERT;
                break;
            case EMBALLAGE:
            case CARTON:
            case PLASTIQUE:
            case CANETTE:
            case CONSERVE:
                couleurAttendue = Couleur.JAUNE;
                break;
            case PAPIER:
                couleurAttendue = Couleur.BLEUE;
                break;
            case AUTRE:
            default:
                couleurAttendue = Couleur.NOIR;
        }

        int points;
        if (cible.getCouleur() == couleurAttendue) {
            points = (int) (quantiteAVider / 5);
        } else {
            points = -(int) (quantiteAVider / 10);
        }
        depot.setPointsAttribues(points);
        depot.setCompte(this.id);
        centre.ajouterDepot(depot);
        this.pointsFidelite += points;

        System.out.println("\nDépôt effectué :");
        System.out.println("- Type : " + typeDechet);
        System.out.println("- Quantité vidée : " + quantiteAVider + " kg dans la poubelle d'ID " + idPoubelle);
        System.out.println("- Points attribués : " + points);
        System.out.println("- Points fidélité actuels : " + this.pointsFidelite);

        depot.setQuantite(depot.getQuantite() - quantiteAVider);
        depot.setDateDepot(null);
        depot.setPointsAttribues(0);
    }  
    
    public void afficherDepots() {
        System.out.println("\nDépôts du compte ID : ");
        for (Depot d : depots) {
            System.out.println("- Type : " + d.getType() + " | Quantité : " + d.getQuantite() + " kg");
        }
    }

} 
