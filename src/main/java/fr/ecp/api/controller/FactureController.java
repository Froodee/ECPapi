package fr.ecp.api.controller;

import fr.ecp.api.entity.Facture;
import fr.ecp.api.repository.FactureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureRepository repository;

    public FactureController(FactureRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Facture> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facture> create(@RequestBody Facture facture) {
        return ResponseEntity.ok(repository.save(facture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> update(@PathVariable Integer id, @RequestBody Facture facture) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        facture.setId(id);
        return ResponseEntity.ok(repository.save(facture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Facture supprimée"));
    }
}
