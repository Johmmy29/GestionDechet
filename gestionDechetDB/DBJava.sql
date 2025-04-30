-- Création de la base de données
CREATE DATABASE IF NOT EXISTS JavaDB;
USE JavaDB;

-- Table pour les comptes (ménages)
CREATE TABLE compte (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    points_fidelite INT DEFAULT 0
);

-- Table pour les types de déchets 
CREATE TABLE type_dechet (
    nom VARCHAR(50) PRIMARY KEY
);

INSERT INTO type_dechet (nom) VALUES 
    ('PLASTIQUE'),
    ('VERRE'),
    ('CARTON'),
    ('METAL');

-- Table pour les couleurs de poubelles 
CREATE TABLE couleur (
    couleur VARCHAR(50) PRIMARY KEY
);

INSERT INTO couleur (couleur) VALUES 
    ('VERTE'),
    ('JAUNE'),
    ('BLEUE'),
    ('CLASSIQUE');

-- Table centre de tri
CREATE TABLE centre_de_tri (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255) NOT NULL
);

-- Table pour les emplacements de poubelles
CREATE TABLE emplacement (
    id_emplacement INT PRIMARY KEY AUTO_INCREMENT,
    adresse VARCHAR(255) NOT NULL,
    quartier VARCHAR(100) NOT NULL,
    id_centre INT NOT NULL,
    FOREIGN KEY (id_centre) REFERENCES centre_de_tri(id)
);

-- Table pour les poubelles
CREATE TABLE poubelle (
    id_poubelle INT PRIMARY KEY AUTO_INCREMENT,
    id_emplacement INT,
    couleur VARCHAR(50) NOT NULL,
    capacite_actuelle FLOAT DEFAULT 0,
    capacite_max FLOAT NOT NULL,
    FOREIGN KEY (id_emplacement) REFERENCES emplacement(id_emplacement),
    FOREIGN KEY (couleur) REFERENCES couleur(couleur)
);

-- Table pour les commerces partenaires
CREATE TABLE commerce (
    id_commerce INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL
);

-- Table pour les contrats de partenariat
CREATE TABLE contrat (
    id_contrat INT PRIMARY KEY AUTO_INCREMENT,
    id_commerce INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    regles_utilisation TEXT,
    FOREIGN KEY (id_commerce) REFERENCES commerce(id_commerce)
);

-- Table pour les catégories de produits
CREATE TABLE categorie_produit (
    id_categorie INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    points_necessaires INT NOT NULL,
    taux_reduction FLOAT NOT NULL,
    id_commerce INT NOT NULL,
    FOREIGN KEY (id_commerce) REFERENCES commerce(id_commerce)
);

-- Table pour les produits
CREATE TABLE produit (
    id_produit INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prix FLOAT NOT NULL,
    id_categorie INT NOT NULL,
    FOREIGN KEY (id_categorie) REFERENCES categorie_produit(id_categorie)
);

-- Table pour les dépôts
CREATE TABLE depot (
    id INT PRIMARY KEY AUTO_INCREMENT,
    compte_id INT NOT NULL,
    type_dechet VARCHAR(50) NOT NULL,
    quantite FLOAT NOT NULL,
    date_depot TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    points_attribues INT DEFAULT 0,
    id_poubelle INT,
    FOREIGN KEY (compte_id) REFERENCES compte(id),
    FOREIGN KEY (type_dechet) REFERENCES type_dechet(nom),
    FOREIGN KEY (id_poubelle) REFERENCES poubelle(id_poubelle)
);

-- Table pour les utilisations de points (bons d'achat/réductions)
CREATE TABLE utilisation_points (
    id INT PRIMARY KEY AUTO_INCREMENT,
    compte_id INT NOT NULL,
    categorie_id INT NOT NULL,
    points_utilises INT NOT NULL,
    date_utilisation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (compte_id) REFERENCES compte(id),
    FOREIGN KEY (categorie_id) REFERENCES categorie_produit(id_categorie)
);