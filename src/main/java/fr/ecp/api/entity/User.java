package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 180, unique = true, nullable = false)
    private String email;

    @Column(name = "roles")
    private String roles;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_verified")
    private boolean isVerified;

    @OneToOne
    @JoinColumn(name = "Utilisateur_id")
    private Utilisateurs utilisateur;
}
