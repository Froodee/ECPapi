package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "email", length = 180)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "service", length = 100)
    private String service;

    @Column(name = "date_rdv")
    private LocalDateTime dateRdv;

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "statut", length = 20)
    private String statut = "en_attente";

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
