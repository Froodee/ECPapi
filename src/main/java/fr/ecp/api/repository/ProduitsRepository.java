package fr.ecp.api.repository;

import fr.ecp.api.entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProduitsRepository extends JpaRepository<Produits, Integer> {
    List<Produits> findByCodeTypId(Integer codeTypId);
    List<Produits> findByLibPdsContainingIgnoreCase(String libPds);
}
