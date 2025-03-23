package gestionDechetTest;

import gestionDechet.*;

public class TestPoubelle {
    public static void main(String[] args) {
      
        Poubelle p = new Poubelle(1, 10, Couleur.JAUNE);
        Poubelle p1 = new Poubelle(2, 5, Couleur.VERT);
        p.afficherInfos();
        p.setCapaciteActuelle(500);     
        p.estPleine();
        p1.setCapaciteActuelle(1000);
        p1.afficherInfos();
        p1.estPleine();
        p.quantiteDechets();      
        
    }
}
