package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "siret", length = 14)
    private String siret;

    @OneToOne
    @JoinColumn(name = "id_user_id")
    private Utilisateurs idUser;
}
