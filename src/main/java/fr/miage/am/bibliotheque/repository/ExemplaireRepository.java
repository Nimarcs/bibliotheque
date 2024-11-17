package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Exemplaire;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    @Query("""
            SELECT e
            from Exemplaire e
            inner join e.oeuvre o
            WHERE o.id = :oeuvreId
            """)
    Stream<Exemplaire> findByOeuvre(Long oeuvreId);

    @Override
    Optional<Exemplaire> findById(Long id);

    @Override
    Exemplaire save(Exemplaire exemplaire) throws ConstraintViolationException;

}