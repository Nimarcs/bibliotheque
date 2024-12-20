package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Usager;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {

    @Query("""
            SELECT u
            from Usager u
            WHERE u.identifiant = :identifiant
            """)
    Optional<Usager> findByIdentifiant(String identifiant);

    @Override
    Optional<Usager> findById(Long id);

    @Override
    Usager save(Usager usager) throws ConstraintViolationException;

    void deleteByIdentifiant(String identifiant);

    @Query("SELECT u FROM Usager u " +
            "WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%', :terme, '%')) " +
            "OR LOWER(u.prenom) LIKE LOWER(CONCAT('%', :terme, '%'))")
    List<Usager> rechercherParNomOuPrenomProche(@Param("terme") String terme);
}