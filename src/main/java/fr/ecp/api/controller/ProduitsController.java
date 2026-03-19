package fr.ecp.api.controller;

import fr.ecp.api.entity.Produits;
import fr.ecp.api.repository.ProduitsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produits")
public class ProduitsController {

    private final ProduitsRepository repository;

    public ProduitsController(ProduitsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Produits> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produits> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Produits> search(@RequestParam String q) {
        return repository.findByLibPdsContainingIgnoreCase(q);
    }

    @GetMapping("/type/{typeId}")
    public List<Produits> getByType(@PathVariable Integer typeId) {
        return repository.findByCodeTypId(typeId);
    }

    @PostMapping
    public ResponseEntity<Produits> create(@RequestBody Produits produit) {
        return ResponseEntity.ok(repository.save(produit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produits> update(@PathVariable Integer id, @RequestBody Produits produit) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        produit.setId(id);
        return ResponseEntity.ok(repository.save(produit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Produit supprimé"));
    }
}
