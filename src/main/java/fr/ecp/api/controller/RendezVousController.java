package fr.ecp.api.controller;

import fr.ecp.api.entity.RendezVous;
import fr.ecp.api.repository.RendezVousRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousRepository repo;

    public RendezVousController(RendezVousRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<RendezVous> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getById(@PathVariable Integer id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RendezVous create(@RequestBody RendezVous rdv) {
        return repo.save(rdv);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> update(@PathVariable Integer id, @RequestBody RendezVous rdv) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        rdv.setId(id);
        return ResponseEntity.ok(repo.save(rdv));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
