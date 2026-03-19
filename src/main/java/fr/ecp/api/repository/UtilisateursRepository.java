package fr.ecp.api.repository;

import fr.ecp.api.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {
    Optional<Utilisateurs> findByEmailUser(String email);
}
