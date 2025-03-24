package gestionDechet;

import java.util.*;

public class CategorieProduit {

    private int idCategorie;
    private String nom;
    private int pointsNecessaires;
    private float tauxReduction;
    private List<Produit> produits;

    public CategorieProduit(int idCategorie, String nom, int pointsNecessaires, float tauxReduction) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.pointsNecessaires = pointsNecessaires;
        this.tauxReduction = tauxReduction;
        this.produits = new ArrayList<>();
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointsNecessaires() {
        return pointsNecessaires;
    }

    public void setPointsNecessaires(int pointsNecessaires) {
        this.pointsNecessaires = pointsNecessaires;
    }

    public float getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(float tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    public boolean verifierReduction(int points) {
    	if (points >= pointsNecessaires) {
    		System.out.println("\nVous avez assez de points pour appliquer la réduction");
    	}
    	else {
    		System.out.println("\nVous n'avez pas encore assez de points pour appliquer la réduction");
    	}
        return points >= pointsNecessaires;
    }

    public float appliquerReduction(float prix, int points) {
        if (verifierReduction(points)) {
            float reduction = prix * (tauxReduction / 100);
            return prix - reduction;
        } else {
            return prix;
        }
    }

    public String toString() {
        return "CategorieProduit{" +
                "idCategorie=" + idCategorie +
                ", nom='" + nom + '\'' +
                ", pointsNecessaires=" + pointsNecessaires +
                ", tauxReduction=" + tauxReduction +
                ", produits=" + produits +
                '}';
    }
    
    public void afficherPrixProduitParId(int idProduit) {
        for (Produit p : produits) {
            if (p.getIdProduit() == idProduit) {
                System.out.println("\nLe prix du produit est de : " + p.getPrix() + " €");
                return;
            }
        }
        System.out.println("nLe produit cherché n'a pas était trouvé !");
    }

    public void afficherNomProduitParId(int idProduit) {
        for (Produit p : produits) {
            if (p.getIdProduit() == idProduit) {
                System.out.println("\nLe nom du produit avec l’ID " + idProduit + " est : " + p.getNom());
                return;
            }
        }
        System.out.println("\nAucun produit n'a été trouvé avec cette ID : " + idProduit);
    }

}