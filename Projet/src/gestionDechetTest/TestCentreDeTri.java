package gestionDechetTest;

import gestionDechet.CentreDeTri;
import gestionDechet.*;
import java.time.LocalDateTime;

public class TestCentreDeTri {

    public static void main(String[] args) {

        CentreDeTri centre = CentreDeTri.INSTANCE;
        centre.placerPoubelle(1, 100, Couleur.VERT);
        centre.afficherInfosPoubelle(1);
        centre.deplacerPoubelle(1, 200);
        centre.afficherInfosPoubelle(1);
        centre.retirerPoubelle(1);
        centre.afficherInfosPoubelle(1);
        centre.creerCommerce(10, "Superette", "12 rue verte", "Alimentation");
        LocalDateTime debut = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusMonths(6);
        centre.creerContrat(100, 10, debut, fin, "Règles contrat");
        centre.creerCompte(101, "camillebeaur29@gmail.com", "java");
        centre.placerPoubelle(1, 100, Couleur.VERT);
        Depot depotVerre = new Depot(500f);
        depotVerre.setType(TypeDechet.VERRE);
        depotVerre.setCompte(101);
        centre.ajouterDepot(depotVerre);
        Depot depotAutre = new Depot(600f);
        depotVerre.setType(TypeDechet.AUTRE);
        depotVerre.setCompte(101);
        centre.ajouterDepot(depotAutre);
        centre.genererStatistiques();
    }
}