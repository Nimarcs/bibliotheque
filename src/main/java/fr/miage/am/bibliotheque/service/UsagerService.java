package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagerService {

    @Autowired
    private static UsagerRepository usagerRepository;

    public static Usager createUsager(Usager usager) {
        return usagerRepository.save(usager);
    }

    public Usager getUsagerByIdentifiant(String identifiant) {
        return usagerRepository.findByIdentifiant(identifiant);
    }

    public List<Usager> getAllUsagers() {
        return usagerRepository.findAll();
    }
}
