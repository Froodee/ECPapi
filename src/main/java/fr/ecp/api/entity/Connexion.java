package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "connexion")
@Data
@NoArgsConstructor
public class Connexion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", length = 32, nullable = false)
    private String login;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "idu")
    private Short idu;
}
