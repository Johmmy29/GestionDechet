package gestionDechet;

import java.util.ArrayList;
import java.util.List;

public class Commerce {
    private int idCommerce;
    private String nom;
    private String adresse;
    private String type;
    private List<CategorieProduit> categories;  // Liste des catégories de produits disponibles dans ce commerce
    private Contrat contrat;  // Contrat associé au commerce

    // Constructeur pour initialiser un commerce
    public Commerce(int idCommerce, String nom, String adresse, String type) {
        this.idCommerce = idCommerce;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.categories = new ArrayList<>();
        this.contrat = null;  // Par défaut, aucun contrat n'est associé
    }

    // Getters et Setters
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

    // Liste des catégories de produits pour ce commerce
    public List<CategorieProduit> getCategories() {
        return categories;
    }

    // Ajoute une catégorie de produit à la liste de catégories du commerce
    public void ajouterCategorie(CategorieProduit categorie) {
        categories.add(categorie);
    }

    // Supprime une catégorie de produit de la liste
    public void supprimerCategorie(CategorieProduit categorie) {
        categories.remove(categorie);
    }

    // Retourne le contrat associé à ce commerce
    public Contrat getContrat() {
        return contrat;
    }

    // Associe un contrat à ce commerce
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    // Vérifie si le commerce a un contrat actif
    public boolean contratActif() {
        return contrat != null && contrat.estActif();
    }

    // Méthode pour afficher les informations de ce commerce sous forme de chaîne
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

    // Affiche toutes les catégories de produits disponibles dans ce commerce
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

    // Affiche tous les produits disponibles dans les catégories de ce commerce
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
