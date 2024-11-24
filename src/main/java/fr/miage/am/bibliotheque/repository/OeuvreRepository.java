package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Oeuvre;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OeuvreRepository extends JpaRepository<Oeuvre, Long> {

    @Query("""
            SELECT o
            from Oeuvre o
            WHERE o.ISBN = :ISBN
            """)
    Optional<Oeuvre> findByISBN(@Param("ISBN") String ISBN);

    @Override
    Optional<Oeuvre> findById(Long id);

    void deleteById(Long id);

    @Override
    Oeuvre save(Oeuvre oeuvre) throws ConstraintViolationException;

}