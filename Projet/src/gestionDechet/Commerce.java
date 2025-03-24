package gestionDechet;

import java.util.ArrayList;
import java.util.List;

public class Commerce {
	private int idCommerce;
    private String nom;
    private String adresse;
    private String type;
    private List<CategorieProduit> categories;
    private Contrat contrat;

    public Commerce(int idCommerce, String nom, String adresse, String type) {
    	this.idCommerce = idCommerce;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.categories = new ArrayList<>();
        this.contrat = null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdCommerce() {
		return idCommerce;
	}

	public void setIdCommerce(int idCommerce) {
		this.idCommerce = idCommerce;
	}

	public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public List<CategorieProduit> getCategories() {
        return categories;
    }
    
    public void ajouterCategorie(CategorieProduit categorie) {
        categories.add(categorie);
    }
    
    public void supprimerCategorie(CategorieProduit categorie) {
        categories.remove(categorie);
    }
    
    public Contrat getContrat() {
        return contrat;
    }
    
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public boolean contratActif() {
        return contrat != null && contrat.estActif();
    }

  
    public String toString() {
        return "Commerce{" +
               "id=" + idCommerce +
               ", nom='" + nom + '\'' +
               ", adresse='" + adresse + '\'' +
               ", type='" + type + '\'' +
               ", nbCatégories=" + categories.size() +
               ", contrat=" + (contrat != null ? "oui" : "non") +
               '}';
    }

    public void afficherCategories() {
        if (categories.isEmpty()) {
            System.out.println("\nCe commerce n’a aucune catégorie de produit enregistrée.");
        } else {
            System.out.println("\nVoici les catégories de produits pour le commerce \"" + nom + "\" :");

            for (CategorieProduit c : categories) {
                System.out.println(" - " + c.getNom()
                    + " | Points nécessaires : " + c.getPointsNecessaires()
                    + " | Réduction : " + c.getTauxReduction() + " %");
            }
        }
    }
    
    public void afficherTousLesProduits() {
        if (categories.isEmpty()) {
            System.out.println("\nCe commerce ne contient aucune catégorie.");
            return;
        }

        System.out.println("\nVoici la liste de tous les produits du commerce \"" + nom + "\" :");

        boolean minUnProduit = false;

        for (CategorieProduit c : categories) {
            List<Produit> produits = c.getProduits();
            if (!produits.isEmpty()) {
                for (Produit p : produits) {
                    System.out.println(" - " + p.getNom() + " (ID : " + p.getIdProduit() + ", Prix : " + p.getPrix() + " €)");
                    minUnProduit = true;
                }
            }
        }

        if (!minUnProduit) {
            System.out.println("Aucun produit n’a encore été ajouté dans les catégories.");
        }
    }
}