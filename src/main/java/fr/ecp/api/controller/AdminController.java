package fr.ecp.api.controller;

import fr.ecp.api.entity.Admin;
import fr.ecp.api.entity.Client;
import fr.ecp.api.entity.Employe;
import fr.ecp.api.repository.AdminRepository;
import fr.ecp.api.repository.ClientRepository;
import fr.ecp.api.repository.EmployeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final EmployeRepository employeRepository;

    public AdminController(AdminRepository adminRepository,
                           ClientRepository clientRepository,
                           EmployeRepository employeRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.employeRepository = employeRepository;
    }

    // --- Admins ---
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Map<String, String>> deleteAdmin(@PathVariable Integer id) {
        if (!adminRepository.existsById(id)) return ResponseEntity.notFound().build();
        adminRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Admin supprimé"));
    }

    // --- Clients ---
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        if (!clientRepository.existsById(id)) return ResponseEntity.notFound().build();
        client.setId(id);
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Integer id) {
        if (!clientRepository.existsById(id)) return ResponseEntity.notFound().build();
        clientRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Client supprimé"));
    }

    // --- Employes ---
    @GetMapping("/employes")
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @PutMapping("/employes/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Integer id, @RequestBody Employe employe) {
        if (!employeRepository.existsById(id)) return ResponseEntity.notFound().build();
        employe.setId(id);
        return ResponseEntity.ok(employeRepository.save(employe));
    }

    @DeleteMapping("/employes/{id}")
    public ResponseEntity<Map<String, String>> deleteEmploye(@PathVariable Integer id) {
        if (!employeRepository.existsById(id)) return ResponseEntity.notFound().build();
        employeRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Employé supprimé"));
    }
}
