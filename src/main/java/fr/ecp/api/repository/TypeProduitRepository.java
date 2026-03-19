package fr.ecp.api.repository;

import fr.ecp.api.entity.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeProduitRepository extends JpaRepository<TypeProduit, Integer> {
}
