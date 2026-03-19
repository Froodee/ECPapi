package fr.ecp.api.controller;

import fr.ecp.api.entity.Utilisateurs;
import fr.ecp.api.repository.UtilisateursRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateursController {

    private final UtilisateursRepository repository;

    public UtilisateursController(UtilisateursRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Utilisateurs> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> update(@PathVariable Integer id, @RequestBody Utilisateurs utilisateur) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        utilisateur.setId(id);
        return ResponseEntity.ok(repository.save(utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Utilisateur supprimé"));
    }
}
