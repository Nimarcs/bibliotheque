CREATE DATABASE IF NOT EXISTS bibliotheque;
USE bibliotheque;

CREATE TABLE IF NOT EXISTS usager(
     id INT NOT NULL AUTO_INCREMENT,
     identifiant VARCHAR(50) NOT NULL,
     nom VARCHAR(50) NOT NULL,
     prenom VARCHAR(50) NOT NULL,
     numTel VARCHAR(50) NOT NULL,
     mail VARCHAR(50) NOT NULL,
     adresse VARCHAR(50) NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO usager (identifiant, nom, prenom, numTel, mail, adresse)
VALUES ('u12345', 'Doe', 'John', '0123456789', 'john.doe@example.com', '123 Rue de l\'Exemple');

SELECT * FROM usager;