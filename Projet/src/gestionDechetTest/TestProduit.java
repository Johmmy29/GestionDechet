package gestionDechetTest;

import gestionDechet.Produit;

public class TestProduit {
    public static void main(String[] args) {

        Produit produit = new Produit(1, "Dentifrice", 2.99f);
        System.out.println("\n" + produit.toString());
    }
}