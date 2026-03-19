package fr.ecp.api.controller;

import fr.ecp.api.entity.Avis;
import fr.ecp.api.repository.AvisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    private final AvisRepository repository;

    public AvisController(AvisRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Avis> getAll() {
        return repository.findAll();
    }

    @GetMapping("/produit/{produitId}")
    public List<Avis> getByProduit(@PathVariable Integer produitId) {
        return repository.findByRefPdsId(produitId);
    }

    @PostMapping
    public ResponseEntity<Avis> create(@RequestBody Avis avis) {
        return ResponseEntity.ok(repository.save(avis));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> update(@PathVariable Integer id, @RequestBody Avis avis) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        avis.setId(id);
        return ResponseEntity.ok(repository.save(avis));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Avis supprimé"));
    }
}
