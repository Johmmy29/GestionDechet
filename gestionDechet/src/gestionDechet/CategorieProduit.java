package gestionDechet;

import java.util.*;

public class CategorieProduit {
    // Attributs représentant une catégorie de produits (ex. alimentaire, électronique, etc.)
    private int idCategorie;
    private String nom;
    private int pointsNecessaires;  
    private float tauxReduction;    
    private List<Produit> produits; 

    // Constructeur pour initialiser une catégorie de produits
    public CategorieProduit(int idCategorie, String nom, int pointsNecessaires, float tauxReduction) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.pointsNecessaires = pointsNecessaires;
        this.tauxReduction = tauxReduction;
        this.produits = new ArrayList<>();
    }

    // Getters et Setters pour chaque attribut
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

    // Méthode pour ajouter un produit à la catégorie
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    // Méthode pour vérifier si un utilisateur a assez de points pour bénéficier d'une réduction
    public boolean verifierReduction(int points) {
        if (points >= pointsNecessaires) {
            System.out.println("\nVous avez assez de points pour appliquer la réduction");
        } else {
            System.out.println("\nVous n'avez pas encore assez de points pour appliquer la réduction");
        }
        return points >= pointsNecessaires;
    }

    // Applique la réduction sur le prix d'un produit en fonction des points de fidélité
    public float appliquerReduction(float prix, int points) {
        if (verifierReduction(points)) {
            float reduction = prix * (tauxReduction / 100);  // Calcul du montant de la réduction
            return prix - reduction;
        } else {
            return prix;  // Pas de réduction si l'utilisateur n'a pas assez de points
        }
    }

    // Méthode pour afficher les informations d'une catégorie de produit sous forme de chaîne
    public String toString() {
        return "CategorieProduit{" +
                "idCategorie=" + idCategorie +
                ", nom='" + nom + '\'' +
                ", pointsNecessaires=" + pointsNecessaires +
                ", tauxReduction=" + tauxReduction +
                ", produits=" + produits +
                '}';
    }
    
    // Affiche le prix d'un produit spécifié par son ID dans la catégorie
    public void afficherPrixProduitParId(int idProduit) {
        for (Produit p : produits) {
            if (p.getIdProduit() == idProduit) {
                System.out.println("\nLe prix du produit est de : " + p.getPrix() + " €");
                return;
            }
        }
        System.out.println("nLe produit cherché n'a pas été trouvé !");
    }

    // Affiche le nom d'un produit spécifié par son ID
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
