package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Emprunt;
import fr.miage.am.bibliotheque.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;

    public void saveEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }
}
