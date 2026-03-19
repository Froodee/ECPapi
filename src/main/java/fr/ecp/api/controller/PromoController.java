package fr.ecp.api.controller;

import fr.ecp.api.entity.Promo;
import fr.ecp.api.repository.PromoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promos")
public class PromoController {

    private final PromoRepository repository;

    public PromoController(PromoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Promo> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promo> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Promo> create(@RequestBody Promo promo) {
        return ResponseEntity.ok(repository.save(promo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promo> update(@PathVariable Integer id, @RequestBody Promo promo) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        promo.setId(id);
        return ResponseEntity.ok(repository.save(promo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Promo supprimée"));
    }
}
