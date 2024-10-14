package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {

    Optional<Usager> findByIdentifiant(String identifiant);

    @Override
    Optional<Usager> findById(Long id);

    @Override
    Usager save(Usager usager);

}