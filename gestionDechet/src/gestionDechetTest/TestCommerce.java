package gestionDechetTest;

import gestionDechet.Commerce;
import gestionDechet.CategorieProduit;
import gestionDechet.Produit;
import gestionDechet.Contrat;
import java.time.LocalDateTime;

public class TestCommerce {

    public static void main(String[] args) {
        Commerce commerce = new Commerce(1, "BioMarket", "123 Rue Verte", "Alimentation");
        commerce.afficherCategories();
        commerce.afficherTousLesProduits();
        CategorieProduit pc = new CategorieProduit(101, "Ordinateurs", 50, 20f);
        CategorieProduit tel = new CategorieProduit(102, "Téléphones", 30, 15f);
        commerce.ajouterCategorie(pc);
        commerce.ajouterCategorie(tel);
        commerce.afficherCategories();
        Produit mac = new Produit(1, "MacBook Air", 1199.99f);
        Produit sam = new Produit(2, "Samsung", 999.99f);
        Produit iphone = new Produit(3, "iPhone", 899.99f);
        pc.ajouterProduit(mac);
        pc.ajouterProduit(sam);
        tel.ajouterProduit(iphone);
        commerce.afficherTousLesProduits();
        commerce.supprimerCategorie(tel);
        commerce.afficherCategories();
        commerce.afficherTousLesProduits();
        LocalDateTime debut = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(30);
        Contrat contrat = new Contrat(2001, commerce.getIdCommerce(), debut, fin, "Engagement sur tri électronique.");
        commerce.setContrat(contrat);
        System.out.println("\nContrat actif ? " + (contrat.estActif() ? "Oui" : "Non"));
        
    }
}
