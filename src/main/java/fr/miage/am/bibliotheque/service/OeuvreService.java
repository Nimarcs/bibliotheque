package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Oeuvre;
import fr.miage.am.bibliotheque.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OeuvreService {
    private final OeuvreRepository oeuvreRepository;

    public OeuvreService(OeuvreRepository oeuvreRepository) {
        this.oeuvreRepository = oeuvreRepository;
    }

    public Oeuvre save(Oeuvre oeuvre) {
        return oeuvreRepository.save(oeuvre);
    }

    public Optional<Oeuvre> findByISBN(String ISBN) {
        return oeuvreRepository.findByISBN(ISBN);
    }

    public List<Oeuvre> findAll() {
        return oeuvreRepository.findAll();
    }

    public void deleteById(Long id) {
        oeuvreRepository.deleteById(id);
    }

    public List<Oeuvre> getAllOeuvres() {
        return oeuvreRepository.findAll();
    }

    // Trouver une œuvre par ID
    @Transactional
    public Optional<Oeuvre> getOeuvreById(Long id) {
        return oeuvreRepository.findById(id);
    }

    // Ajouter une nouvelle œuvre
    public Oeuvre addOeuvre(Oeuvre oeuvre) {
        return oeuvreRepository.save(oeuvre);
    }

    // Supprimer une œuvre par ID
    public void deleteOeuvreById(Long id) {
        oeuvreRepository.deleteById(id);
    }

    // Mettre à jour une œuvre existante
    public Oeuvre updateOeuvre(Long id, Oeuvre updatedOeuvre) {
        return oeuvreRepository.findById(id).map(oeuvre -> {
            oeuvre.setNom(updatedOeuvre.getNom());
            oeuvre.setDatePremiereEdition(updatedOeuvre.getDatePremiereEdition());
            oeuvre.setISBN(updatedOeuvre.getISBN());
            return oeuvreRepository.save(oeuvre);
        }).orElseThrow(() -> new RuntimeException("Œuvre introuvable avec l'ID : " + id));
    }
}
