package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagerService {

    private final UsagerRepository usagerRepository;

    public UsagerService(UsagerRepository usagerRepository) {
        this.usagerRepository = usagerRepository;
    }

    public List<Usager> rechercherUsagers(String terme) {
        return usagerRepository.rechercherParNomOuPrenomProche(terme);
    }

    public Usager getUsagerByIdentifiant(String identifiant) {
        return usagerRepository.findByIdentifiant(identifiant)
                .orElseThrow(() -> new RuntimeException("Usager non trouvé pour l'identifiant : " + identifiant));
    }

    @Transactional
    public void supprimerUsager(String identifiant) {
        usagerRepository.deleteByIdentifiant(identifiant);
    }
}