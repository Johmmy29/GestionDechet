package gestionDechetTest;

import gestionDechet.Contrat;
import java.time.LocalDateTime;

public class TestContrat {

    public static void main(String[] args) {
    	LocalDateTime debut = LocalDateTime.now().minusDays(2); 
        LocalDateTime fin = LocalDateTime.now().plusDays(10); 
        Contrat contrat = new Contrat(1, 101, debut, fin, "Règles : Respect du tri et du recyclage.");
        System.out.println("\nContrat actif ? " + (contrat.estActif() ? "Oui" : "Non"));
        System.out.println(contrat.getDateFin());
        LocalDateTime nouvelleDateValide = LocalDateTime.now().plusDays(30);
        contrat.renouvelerContrat(nouvelleDateValide);
        System.out.println(contrat.getDateFin());
        LocalDateTime dateInvalide = LocalDateTime.now().minusDays(5);
        contrat.renouvelerContrat(dateInvalide);
        System.out.println(contrat.getDateFin());      
        
    }
}