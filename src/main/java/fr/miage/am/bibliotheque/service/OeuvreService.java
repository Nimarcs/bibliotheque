package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Exemplaire;
import fr.miage.am.bibliotheque.modele.Oeuvre;
import fr.miage.am.bibliotheque.repository.ExemplaireRepository;
import fr.miage.am.bibliotheque.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class OeuvreService {
    private final OeuvreRepository oeuvreRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final ExemplaireService exemplaireService;


    public OeuvreService(OeuvreRepository oeuvreRepository, ExemplaireRepository exemplaireRepository, ExemplaireService exemplaireService) {
        this.oeuvreRepository = oeuvreRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.exemplaireService = exemplaireService;
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

    /**
     * @param ISBN isbn
     * @throws NullPointerException isbn non valide
     */
    @Transactional
    public void supprimerOeuvre(String ISBN) throws NullPointerException {
        Optional<Oeuvre> oeuvre = oeuvreRepository.findByISBN(ISBN);
        if (oeuvre.isEmpty()) throw new NullPointerException("ISBN non valide");

        Stream<Exemplaire> exemplaireStream = exemplaireRepository.findByOeuvre(oeuvre.get().getId());
        exemplaireStream.forEach(exemplaire -> exemplaireRepository.deleteById(exemplaire.getId()));
        //TODO remplacer par supprimer de service pas du répository pour continuer l'effet boule de neige
        oeuvreRepository.deleteById(oeuvre.get().getId());
    }

    public List<Oeuvre> getAllOeuvres() {
        return oeuvreRepository.findAll();
    }
}
