package fr.miage.am.bibliotheque.controller;

import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionBackOffice {

    @Autowired
    UsagerRepository usagerRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public String creerUsager(String nom, String prenom, String numTel, String mail, String adresse) {
        Usager usager = new Usager(nom, prenom, numTel, mail, adresse);
        usagerRepository.save(usager);
        entityManager.refresh(usager);
        return usager.getIdentifiant();
    }
}
