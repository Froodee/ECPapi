package fr.ecp.api.controller;

import fr.ecp.api.entity.TypeProduit;
import fr.ecp.api.repository.TypeProduitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/type-produits")
public class TypeProduitController {

    private final TypeProduitRepository repository;

    public TypeProduitController(TypeProduitRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TypeProduit> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeProduit> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeProduit> create(@RequestBody TypeProduit type) {
        return ResponseEntity.ok(repository.save(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeProduit> update(@PathVariable Integer id, @RequestBody TypeProduit type) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        type.setId(id);
        return ResponseEntity.ok(repository.save(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Type supprimé"));
    }
}
