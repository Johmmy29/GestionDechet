-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestion_dechet;
USE gestion_dechet;

-- Table Compte
CREATE TABLE IF NOT EXISTS compte (
    id INT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    points_fidelite INT DEFAULT 0
);

-- Table TypeDechet (pour référence)
CREATE TABLE IF NOT EXISTS type_dechet (
    nom VARCHAR(50) PRIMARY KEY
);

-- Insérer les types de déchets (enumération)
INSERT IGNORE INTO type_dechet (nom) VALUES 
('CARTON'), ('VERRE'), ('CONSERVE'), ('PAPIER'), 
('CANETTE'), ('EMBALLAGE'), ('AUTRE'), ('PLASTIQUE');

-- Table Depot
CREATE TABLE IF NOT EXISTS depot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    compte_id INT,
    type_dechet VARCHAR(50),
    quantite FLOAT NOT NULL,
    date_depot TIMESTAMP NULL,
    points_attribues INT DEFAULT 0,
    FOREIGN KEY (compte_id) REFERENCES compte(id),
    FOREIGN KEY (type_dechet) REFERENCES type_dechet(nom)
);

-- Table Poubelle
CREATE TABLE IF NOT EXISTS poubelle (
    id_poubelle INT PRIMARY KEY,
    id_emplacement INT NOT NULL,
    couleur VARCHAR(20) NOT NULL,
    capacite_actuelle FLOAT DEFAULT 0.0,
    capacite_max FLOAT DEFAULT 1000.0
);

-- Table Commerce
CREATE TABLE IF NOT EXISTS commerce (
    id_commerce INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(200) NOT NULL,
    type VARCHAR(50) NOT NULL
);

-- Table Contrat
CREATE TABLE IF NOT EXISTS contrat (
    id_contrat INT PRIMARY KEY,
    id_commerce INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    regles_utilisation TEXT,
    FOREIGN KEY (id_commerce) REFERENCES commerce(id_commerce)
);

-- Table CategorieProduit
CREATE TABLE IF NOT EXISTS categorie_produit (
    id_categorie INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    points_necessaires INT NOT NULL,
    taux_reduction FLOAT NOT NULL,
    id_commerce INT,
    FOREIGN KEY (id_commerce) REFERENCES commerce(id_commerce)
);

-- Table Produit
CREATE TABLE IF NOT EXISTS produit (
    id_produit INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prix FLOAT NOT NULL,
    id_categorie INT,
    FOREIGN KEY (id_categorie) REFERENCES categorie_produit(id_categorie)
);