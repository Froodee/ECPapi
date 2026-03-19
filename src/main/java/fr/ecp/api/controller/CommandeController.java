package fr.ecp.api.controller;

import fr.ecp.api.entity.Commande;
import fr.ecp.api.repository.CommandeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeRepository repository;

    public CommandeController(CommandeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Commande> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Commande> getByUser(@PathVariable Integer userId) {
        return repository.findByIdUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Commande> create(@RequestBody Commande commande) {
        return ResponseEntity.ok(repository.save(commande));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> update(@PathVariable Integer id, @RequestBody Commande commande) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        commande.setId(id);
        return ResponseEntity.ok(repository.save(commande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Commande supprimée"));
    }
}
