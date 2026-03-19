package fr.ecp.api.repository;

import fr.ecp.api.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
