package fr.ecp.api.service;

import fr.ecp.api.dto.LoginRequest;
import fr.ecp.api.dto.LoginResponse;
import fr.ecp.api.dto.RegisterRequest;
import fr.ecp.api.entity.Admin;
import fr.ecp.api.entity.Client;
import fr.ecp.api.entity.Employe;
import fr.ecp.api.entity.User;
import fr.ecp.api.entity.Utilisateurs;
import fr.ecp.api.repository.AdminRepository;
import fr.ecp.api.repository.ClientRepository;
import fr.ecp.api.repository.EmployeRepository;
import fr.ecp.api.repository.UserRepository;
import fr.ecp.api.repository.UtilisateursRepository;
import fr.ecp.api.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UtilisateursRepository utilisateursRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final EmployeRepository employeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       UtilisateursRepository utilisateursRepository,
                       AdminRepository adminRepository,
                       ClientRepository clientRepository,
                       EmployeRepository employeRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.utilisateursRepository = utilisateursRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.employeRepository = employeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        String roles = user.getRoles() != null ? user.getRoles() : "ROLE_USER";
        // Nettoyer le format JSON de Symfony: ["ROLE_USER"] -> ROLE_USER
        roles = roles.replaceAll("[\\[\\]\"]", "").trim();

        String token = jwtUtil.generateToken(user.getEmail(), roles);

        Utilisateurs utilisateurs = user.getUtilisateur();
        Integer utilisateurId = utilisateurs != null ? utilisateurs.getId() : null;
        String nomUser = utilisateurs != null ? utilisateurs.getNomUser() : user.getEmail();

        return new LoginResponse(token, user.getEmail(), roles, utilisateurId, nomUser);
    }

    @Transactional
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Créer Utilisateurs
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setNomUser(request.getNomUser());
        utilisateurs.setAdrUser(request.getAdrUser() != null ? request.getAdrUser() : "");
        utilisateurs.setCpUser(request.getCpUser() != null ? request.getCpUser() : "00000");
        utilisateurs.setVilleUser(request.getVilleUser() != null ? request.getVilleUser() : "");
        utilisateurs.setNumUser(request.getNumUser() != null ? request.getNumUser() : "");
        utilisateurs.setEmailUser(request.getEmail());
        utilisateurs.setIdUser((short) 0);
        utilisateurs = utilisateursRepository.save(utilisateurs);

        // Créer User (Symfony security)
        String role = request.getRole() != null ? request.getRole().toUpperCase() : "CLIENT";
        String roles = "[\"ROLE_" + role + "\",\"ROLE_USER\"]";

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        user.setVerified(true);
        user.setUtilisateur(utilisateurs);
        user = userRepository.save(user);

        // Créer le sous-type selon le rôle
        if ("ADMIN".equals(role)) {
            Admin admin = new Admin();
            admin.setIdUser(utilisateurs);
            adminRepository.save(admin);
        } else if ("EMPLOYE".equals(role)) {
            Employe employe = new Employe();
            employe.setIdUser(utilisateurs);
            employe.setNoSecu("0000000000000");
            employe.setDateEmp(java.time.LocalDate.now());
            employeRepository.save(employe);
        } else {
            Client client = new Client();
            client.setIdUser(utilisateurs);
            clientRepository.save(client);
        }

        String cleanRoles = "ROLE_" + role + ",ROLE_USER";
        String token = jwtUtil.generateToken(user.getEmail(), cleanRoles);

        return new LoginResponse(token, user.getEmail(), cleanRoles, utilisateurs.getId(), utilisateurs.getNomUser());
    }
}
