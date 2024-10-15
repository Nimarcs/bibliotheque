CREATE DATABASE IF NOT EXISTS bibliotheque;
USE bibliotheque;

CREATE TABLE IF NOT EXISTS usager
(
    id          INT         NOT NULL AUTO_INCREMENT,
    identifiant VARCHAR(50) NOT NULL,
    nom         VARCHAR(50) NOT NULL,
    prenom      VARCHAR(50) NOT NULL,
    numTel      VARCHAR(50) NOT NULL,
    mail        VARCHAR(50) NOT NULL,
    adresse     VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE usager ADD CONSTRAINT identifiant_UNI UNIQUE (identifiant);

CREATE FUNCTION generate_identifiant(nom VARCHAR(50))
    RETURNS VARCHAR(50)
    DETERMINISTIC
BEGIN
    DECLARE base_identifiant VARCHAR(50);
    DECLARE suffix INT DEFAULT 1;
    DECLARE final_identifiant VARCHAR(50);

    -- Convert last name to lowercase and use it as the base
    SET base_identifiant = LOWER(nom);

    -- Check if similar identifiants exist and find the max suffix
    SELECT count(*) + 1
    into suffix
    from usager
    where identifiant like Concat(base_identifiant, '%');

    -- Concatenate the base and suffix to create the final identifiant
    SET final_identifiant = CONCAT(base_identifiant, suffix);

    RETURN final_identifiant;
END;


CREATE TRIGGER before_usager_insert
    BEFORE INSERT
    ON usager
    FOR EACH ROW
BEGIN
    -- Check if identifiant is not provided, then generate it
    IF NEW.identifiant IS NULL OR NEW.identifiant = '' THEN
        SET NEW.identifiant = generate_identifiant(NEW.nom);
    END IF;
END;



INSERT INTO usager (identifiant, nom, prenom, numTel, mail, adresse)
VALUES ('u12345', 'Doe', 'John', '0123456789', 'john.doe@example.com', '123 Rue de l\'Exemple');

SELECT *
FROM usager;