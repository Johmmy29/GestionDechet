package gestionDechetTest;

import gestionDechet.Depot;
import gestionDechet.TypeDechet;
import java.time.LocalDateTime;

public class TestDepot {
    public static void main(String[] args) {

        Depot depot = new Depot(10f);
        depot.setCompte(123);
        depot.setType(TypeDechet.PAPIER);
        depot.setPointsAttribues(5);
        depot.setDateDepot(LocalDateTime.now());
        System.out.println("ID Compte : " + depot.getCompte());
        System.out.println("Type de déchet : " + depot.getType());
        System.out.println("Quantité : " + depot.getQuantite() + " kg");
        System.out.println("Points attribués : " + depot.getPointsAttribues());
        System.out.println("Date du dépôt : " + depot.getDateDepot());
    }
}