package fr.ecp.api.repository;

import fr.ecp.api.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
