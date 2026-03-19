package fr.ecp.api.repository;

import fr.ecp.api.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
    List<Avis> findByRefPdsId(Integer produitId);
}
