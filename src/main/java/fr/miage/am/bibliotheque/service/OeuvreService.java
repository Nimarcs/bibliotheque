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
