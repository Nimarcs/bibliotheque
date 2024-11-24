package fr.miage.am.bibliotheque.service;

import fr.miage.am.bibliotheque.modele.Exemplaire;
import fr.miage.am.bibliotheque.modele.Oeuvre;
import fr.miage.am.bibliotheque.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public Optional<Exemplaire> identifier(Oeuvre oeuvre) {
        Stream<Exemplaire> exemplaireStream = exemplaireRepository.findByOeuvre(oeuvre.getId());
        return exemplaireStream.filter(Exemplaire::isDisponible).findFirst();
    }

    @Transactional(readOnly = true)
    public Optional<Exemplaire> identifier(Oeuvre oeuvre, Integer numero) {
        Stream<Exemplaire> exemplaireStream = exemplaireRepository.findByOeuvre(oeuvre.getId());
        List<Exemplaire> exemplaireList = exemplaireStream.filter(exemplaire -> Objects.equals(exemplaire.getNumero(), numero)).toList();
        if (exemplaireList.size() > 1)
            throw new IllegalStateException("Multiple exemplaire trouve avec " + oeuvre.getNom() + " n°" + numero);
        return Optional.ofNullable(exemplaireList.size() == 1 ? exemplaireList.getFirst() : null);
    }

    public void saveExemplaire(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

}
