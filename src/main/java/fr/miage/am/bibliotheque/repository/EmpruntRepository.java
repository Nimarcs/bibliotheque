package fr.miage.am.bibliotheque.repository;

import fr.miage.am.bibliotheque.modele.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    // Trouver tous les emprunts d'un usager
    List<Emprunt> findByUsagerId(Long usagerId);

    // Trouver tous les emprunts associés à un exemplaire donné
    List<Emprunt> findByExemplaireId(Long exemplaireId);

    // Trouver les emprunts non retournés d'un exemplaire
    List<Emprunt> findByExemplaireIdAndDateRetourReelIsNull(Long exemplaireId);

    // Optionnel : Trouver tous les emprunts actifs (non retournés)
    List<Emprunt> findByDateRetourReelIsNull();
}
