package gestionDechet;

import java.util.*;
import java.time.LocalDateTime;

public class CentreDeTri {
    
    public static final CentreDeTri INSTANCE = new CentreDeTri();  // Singleton pour garantir une seule instance du centre de tri

    private List<Contrat> partenariats;   // Liste des contrats avec les commerces partenaires
    private List<Poubelle> poubelles;     // Liste des poubelles gérées par le centre de tri
    private List<Depot> historiqueDepots; // Historique des dépôts effectués
    private List<Commerce> commerces;     // Liste des commerces partenaires
    private List<Compte> comptes;         // Liste des comptes utilisateurs

    public CentreDeTri() {
        this.partenariats = new ArrayList<>();
        this.poubelles = new ArrayList<>();
        this.historiqueDepots = new ArrayList<>();
        this.commerces = new ArrayList<>();
        this.comptes = new ArrayList<>();
    }

    // Méthode pour ajouter un dépôt à l'historique des dépôts
    public void ajouterDepot(Depot depot) {
        this.historiqueDepots.add(depot);
    }

    // Getters et Setters pour les attributs
    public List<Contrat> getPartenariats() {
        return partenariats;
    }

    public void setPartenariats(List<Contrat> partenariats) {
        this.partenariats = partenariats;
    }

    public List<Poubelle> getPoubelles() {
        return poubelles;
    }

    public void setPoubelles(List<Poubelle> poubelles) {
        this.poubelles = poubelles;
    }

    public List<Depot> getHistoriqueDepots() {
        return historiqueDepots;
    }

    public void setHistoriqueDepots(List<Depot> historiqueDepots) {
        this.historiqueDepots = historiqueDepots;
    }

    public List<Commerce> getCommerces() {
        return commerces;
    }

    public void setCommerces(List<Commerce> commerces) {
        this.commerces = commerces;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    // Méthode pour placer une poubelle à un emplacement donné
    public boolean placerPoubelle(int idPoubelle, int idEmplacement, Couleur couleur) {
        for (Poubelle p : poubelles) {
            if (p.getIdPoubelle() == idPoubelle) {
                System.out.println("\nUne poubelle possède déjà cet ID " + idPoubelle);
                return false;
            }
        }

        Poubelle nouvellePoubelle = new Poubelle(idPoubelle, idEmplacement, couleur);
        poubelles.add(nouvellePoubelle);
        System.out.println("\nLa poubelle d'ID " + idPoubelle + " de couleur " + couleur + " a bien été ajoutée à l'emplacement " + idEmplacement);
        return true;
    }

    // Méthode pour déplacer une poubelle d'un emplacement à un autre
    public boolean deplacerPoubelle(int idPoubelle, int nouvelEmplacement) {
        for (Poubelle p : poubelles) {
            if (p.getIdPoubelle() == idPoubelle) {
                int ancien = p.getIdEmplacement();
                p.setIdEmplacement(nouvelEmplacement);
                System.out.println("\nLa Poubelle d'ID " + idPoubelle + " a bien été déplacée de l'emplacement " + ancien + " vers " + nouvelEmplacement);
                return true;
            }
        }
        System.out.println("\nAucune poubelle n'a été trouvée avec cette ID : " + idPoubelle);
        return false;
    }

    // Collecte les déchets d'une poubelle et réinitialise sa capacité actuelle
    public boolean collecterDechets(int idPoubelle) {
        for (Poubelle p : poubelles) {
            if (p.getIdPoubelle() == idPoubelle) {
                float quantiteAvant = p.getCapaciteActuelle();
                p.vider();
                System.out.println("\nDéchets collectés dans la poubelle ID " + idPoubelle + " (" + quantiteAvant + " kg vidés).");
                return true;
            }
        }
        System.out.println("\nAucune poubelle trouvée avec l'ID " + idPoubelle);
        return false;
    }

    // Génére des statistiques sur les dépôts effectués dans le centre de tri
    public void genererStatistiques() {
        System.out.println("\nSTATISTIQUES DES DÉPÔTS DU CENTRE DE TRI");
        calculerTotalDechets();
        afficherNombreTotalDepots();
        afficherDepotMax();
        afficherPoidsMoyen();
        System.out.println("\nFin du rapport statistique.");
    }

    // Calcul du total des déchets déposés dans le centre de tri
    public void calculerTotalDechets() {
        float total = 0;
        for (Depot depot : historiqueDepots) {
            total += depot.getQuantite();
        }
        System.out.println("\nLa Quantité totale de déchets est de : " + total + " kg");
    }

    // Affiche le nombre total de dépôts effectués dans le centre de tri
    public void afficherNombreTotalDepots() {
        System.out.println("\nLe nombre total de dépôts est de : " + historiqueDepots.size());
    }

    // Affiche le dépôt ayant le plus grand poids
    public void afficherDepotMax() {
        if (historiqueDepots.isEmpty()) {
            System.out.println("\nAucun dépôt enregistré.");
            return;
        }

        Depot depotMax = historiqueDepots.get(0);
        for (Depot depot : historiqueDepots) {
            if (depot.getQuantite() > depotMax.getQuantite()) {
                depotMax = depot;
            }
        }

        System.out.println("\nLe dépôt le plus lourd est de " + depotMax.getQuantite() + " kg");
    }

    // Affiche le poids moyen des dépôts
    public void afficherPoidsMoyen() {
        if (historiqueDepots.isEmpty()) {
            System.out.println("\nAucun dépôt n'est enregistré.");
            return;
        }

        float total = 0;
        for (Depot depot : historiqueDepots) {
            total += depot.getQuantite();
        }

        float moyenne = total / historiqueDepots.size();
        System.out.println("\nLe poids moyen des dépôts est de " + moyenne + " kg");
    }

    // Crée un nouveau contrat entre un commerce et le centre de tri
    public boolean creerContrat(int idContrat, int idCommerce, LocalDateTime debut, LocalDateTime fin, String regles) {
        for (Commerce commerce : commerces) {
            if (commerce.getIdCommerce() == idCommerce) {
                if (commerce.getContrat() == null) {
                    Contrat contrat = new Contrat(idContrat, idCommerce, debut, fin, regles);
                    partenariats.add(contrat);

                    System.out.println("\nContrat créé et associé au commerce ID : " + idCommerce);
                    return true;
                } else {
                    System.out.println("\nCe commerce possède déjà un contrat actif ou existant.");
                    return false;
                }
            }
        }
        System.out.println("\nCommerce introuvable avec l'ID : " + idCommerce);
        return false;
    }
    
    // Crée un commerce dans le centre de tri
    public boolean creerCommerce(int idCommerce, String nom, String adresse, String type) {
        for (Commerce c : commerces) {
            if (c.getIdCommerce() == idCommerce) {
                System.out.println("\nUn commerce avec l'ID " + idCommerce + " existe déjà.");
                return false;
            }
        }
        Commerce nouveau = new Commerce(idCommerce, nom, adresse, type);
        commerces.add(nouveau);

        System.out.println("\nCommerce créé avec succès : " + nom + " (ID : " + idCommerce + ")");
        return true;
    }

    // Affiche les informations d'une poubelle spécifiée par son ID
    public void afficherInfosPoubelle(int idPoubelle) {
        for (Poubelle p : poubelles) {
            if (p.getIdPoubelle() == idPoubelle) {
                p.afficherInfos();
                return;
            }
        }
        System.out.println("\nAucune poubelle trouvée avec l'ID : " + idPoubelle);
    }
    
    // Collecte les déchets de toutes les poubelles pleines
    public void collecterToutesLesPoubellesPleines() {
        for (Poubelle p : poubelles) {
            if (p.pleine()) {
                collecterDechets(p.getIdPoubelle());
            }
        }
    }

    // Renouvelle un contrat existant
    public boolean renouvelerContrat(int idContrat, LocalDateTime nouvelleDateFin) {
        for (Contrat c : partenariats) {
            if (c.getIdContrat() == idContrat) {
                return c.renouvelerContrat(nouvelleDateFin);
            }
        }
        System.out.println("\nAucun contrat trouvé avec l'ID " + idContrat);
        return false;
    }

    // Affiche les catégories disponibles pour un commerce spécifique
    public void afficherCategoriesParCommerce(int idCommerce) {
        for (Commerce commerce : commerces) {
            if (commerce.getIdCommerce() == idCommerce) {
                commerce.afficherCategories();
                return;
            }
        }
        System.out.println("\nAucun commerce trouvé avec l'ID " + idCommerce);
    }

    // Affiche les produits d'un commerce spécifique
    public void afficherProduitsCommerce(int idCommerce) {
        for (Commerce c : commerces) {
            if (c.getIdCommerce() == idCommerce) {
                c.afficherTousLesProduits();
                return;
            }
        }
        System.out.println("\nAucun commerce trouvé avec l'ID : " + idCommerce);
    }
    
    // Crée un compte pour un utilisateur
    public boolean creerCompte(int id, String email, String motDePasse) {
        for (Compte c : comptes) {
            if (c.getId() == id || c.getEmail().equalsIgnoreCase(email)) {
                System.out.println("\nUn compte avec cet ID ou cet email existe déjà.");
                return false;
            }
        }

        Compte nouveau = new Compte(id, email, motDePasse);
        comptes.add(nouveau);
        System.out.println("\nCompte créé avec succès : " + email + " (ID : " + id + ")");
        return true;
    }
}
