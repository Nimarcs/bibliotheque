package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Emprunt;
import fr.miage.am.bibliotheque.modele.Exemplaire;
import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.repository.EmpruntRepository;
import fr.miage.am.bibliotheque.repository.ExemplaireRepository;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private UsagerRepository usagerRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Emprunt> getAllEmpruntEnCours() {
        return empruntRepository.findAll().stream().filter(emprunt -> emprunt.getDateRetourReel() == null).toList();
    }

    @Transactional
    public Optional<Emprunt> getEmpruntById(Long id) {
        return empruntRepository.findById(id);
    }

    public void saveEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);

        Usager usager = emprunt.getUsager();
        usager.ajouterEmprunt(emprunt);
        usagerRepository.save(usager);

        Exemplaire exemplaire = emprunt.getExemplaire();
        exemplaire.ajouterEmprunt(emprunt);
        exemplaireRepository.save(exemplaire);
    }

    public void rendre(Emprunt emprunt) {
        emprunt.setDateRetourReel(new Date());
        empruntRepository.save(emprunt);
    }
}
