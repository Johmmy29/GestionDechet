package gestionDechetTest;

import gestionDechet.*;

public class TestCompte {
    public static void main(String[] args) {
    	Compte compte = new Compte(1, "camillebeaur29@gmail.com", "java");
    	compte.afficherDepots();
    	for (Depot d : compte.getDepots()) {
            if (d.getType() == TypeDechet.VERRE) {
                d.setQuantite(60f);
                break;
            }
        }
    	compte.afficherDepots();
    	CentreDeTri.INSTANCE.placerPoubelle(200, 10, Couleur.VERT);
    	compte.viderDepot(200, TypeDechet.VERRE);
    	compte.consulterPoints();
    }
}