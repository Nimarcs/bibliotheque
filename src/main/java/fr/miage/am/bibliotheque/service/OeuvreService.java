package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Oeuvre;
import fr.miage.am.bibliotheque.repository.OeuvreRepository;
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

    public Optional<Oeuvre> findById(Long id) {
        return oeuvreRepository.findById(id);
    }

    public Optional<Oeuvre> findByISBN(String ISBN) {
        return oeuvreRepository.findByISBN(ISBN);
    }

    public List<Oeuvre> findAll() {
        return oeuvreRepository.findAll();
    }

    // TODO penser à gérer les exemplaires
    public void deleteById(Long id) {
        oeuvreRepository.deleteById(id);
    }

    public List<Oeuvre> getAllOeuvres() {
        return oeuvreRepository.findAll();
    }
}
