
-- Centre de tri
INSERT INTO centre_de_tri (nom, adresse) VALUES 
    ('Cauchy', 'Rue du Parc 95000');

-- Emplacements
INSERT INTO emplacement (id_emplacement) VALUES 
    (100),
    (101),
    (102);

-- Poubelles
INSERT INTO poubelle (id_poubelle, id_emplacement, couleur, capacite_max) VALUES 
    (1, 100, 'VERTE', 100.0),
    (2, 101, 'JAUNE', 100.0),
    

-- Comptes utilisateurs
INSERT INTO compte (id, email, mot_de_passe, points_fidelite) VALUES 
    (1, 'kurestheot@cy-tech.fr', 'mdpcytech', 150),
    (2, 'javaProjet@proton.net', 'mdpProjet', 75);
    

-- Commerces
INSERT INTO commerce (id_commerce, nom, adresse, type) VALUES 
    (1, 'Pixel', 'Cauchy', 'Jeux Videos'),
    (2, 'Guide', 'CAuchy', 'Jeux de societe');

-- Contrats
INSERT INTO contrat (id_contrat, id_commerce, date_debut, date_fin, regles_utilisation) VALUES 
    (1, 1, '2025-01-01 00:00:00', '2025-12-31 23:59:59', 'Réduction sur toutes les LAN'),
    (2, 2, '2025-02-01 00:00:00', '2025-11-30 23:59:59', 'Réduction sur les Tavernes');

-- Catégories de produits
INSERT INTO categorie_produit (id_categorie, nom, points_necessaires, taux_reduction, id_commerce) VALUES 
    (1, 'Shampoing', 50, 0.05, 1),
    (2, 'Nettoyage', 20, 0.1, 1);


-- Produits
INSERT INTO produit (id_produit, nom, prix, id_categorie) VALUES 
    (1, 'Shampoing Garnier', 2.50, 1),
    (2, 'Javel', 8.99, 2);
    

-- Dépôts
INSERT INTO depot (compte_id, type_dechet, quantite, date_depot, points_attribues, id_poubelle) VALUES 
    (1, 'VERRE', 5, '2025-01-15 10:30:00', 5, 1),
    (1, 'PLASTIQUE', 2, '2025-01-16 14:45:00', 4, 2),
    (2, 'CARTON', 3, '2025-01-15 16:20:00', 5, 3),
    (3, 'METAL', 1, '2025-01-17 09:15:00', 2, 5);

-- Utilisations de points
INSERT INTO utilisation_points (compte_id, categorie_id, points_utilises, date_utilisation) VALUES 
    (1, 1, 100, '2025-01-20 11:30:00'),
    (3, 3, 200, '2025-01-21 15:45:00');