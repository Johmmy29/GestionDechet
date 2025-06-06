package gestionDechet;

public class Poubelle {

    private int idPoubelle; 
    private final float capaciteMax = 1000.0f; 
    private float capaciteActuelle; 
    private int idEmplacement; 
    private Couleur couleur; 
    
    // Constructeur pour initialiser une poubelle avec un ID, un emplacement et une couleur
    public Poubelle(int idPoubelle, int idEmplacement, Couleur couleur) {
        this.idPoubelle = idPoubelle;
        this.idEmplacement = idEmplacement;
        this.couleur = couleur;
        this.capaciteActuelle = 0.0f; // La poubelle est vide au départ
    }

    // Getters et Setters
    public int getIdPoubelle() {
        return idPoubelle;
    }

    public void vider() {
        this.capaciteActuelle = 0; // Vide la poubelle
    }

    public int getIdEmplacement() {
        return idEmplacement;
    }
    
    public void setIdEmplacement(int idEmplacement) {
        this.idEmplacement = idEmplacement;
    }
    
    public boolean pleine() {
        return capaciteActuelle >= capaciteMax; // Vérifie si la poubelle est pleine
    }

    public Couleur getCouleur() {
        return couleur;
    }
    
    public float getCapaciteActuelle() {
        return capaciteActuelle;
    }

    public void setCapaciteActuelle(float capaciteActuelle) {
        this.capaciteActuelle = capaciteActuelle;
    }

    public float getCapaciteMax() {
        return capaciteMax;
    }

    // Affiche les informations de la poubelle
    public void afficherInfos() {
        System.out.println("ID Poubelle : " + idPoubelle +
                           " | Emplacement : " + idEmplacement +
                           " | Couleur : " + couleur +
                           " | Capacité : " + capaciteActuelle + "/" + capaciteMax);
    }
    
    // Affiche l'espace disponible dans la poubelle
    public void quantiteDechets() {
        System.out.println("La poubelle contient " + capaciteActuelle + " kg sur " + capaciteMax + " kg. Il reste donc " + (capaciteMax - capaciteActuelle) + " kg de libre.");
    }
    
    // Affiche si la poubelle est pleine ou non
    public void estPleine() {
        if (this.capaciteMax == this.capaciteActuelle) {
            System.out.println("La poubelle est pleine");
        } else {
            System.out.println("La poubelle n'est pas encore pleine");
        }
    }
}
