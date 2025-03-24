package gestionDechet;

public class Produit {

    private int idProduit;
    private String nom;
    private float prix;

    public Produit(int idProduit, String nom, float prix) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prix = prix;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String toString() {
        return "Produit{" +
                "idProduit='" + idProduit + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}