package gestionDechetTest;

import gestionDechet.CategorieProduit;
import gestionDechet.Produit;

public class TestCategorieProduit {
    public static void main(String[] args) {

        Produit p1 = new Produit(101, "Savon", 3.50f);
        Produit p2 = new Produit(102, "Shampooing", 5.80f);
        CategorieProduit hygiene = new CategorieProduit(1, "Hygiène", 10, 20.0f);
        hygiene.ajouterProduit(p1);
        hygiene.ajouterProduit(p2);
        hygiene.afficherNomProduitParId(101);
        hygiene.verifierReduction(5);
        hygiene.verifierReduction(15);
        float prixFinal = hygiene.appliquerReduction(10.0f, 15);
        System.out.println("\nPrix après réduction : " + prixFinal + " €");
        
    }
}
