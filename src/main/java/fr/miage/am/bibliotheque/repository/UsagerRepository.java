package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {
    Usager findByIdentifiant(String identifiant);
}