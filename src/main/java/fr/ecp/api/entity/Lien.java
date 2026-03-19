package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lien")
@Data
@NoArgsConstructor
public class Lien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user_con_id")
    private Connexion connexion;

    @OneToOne
    @JoinColumn(name = "id_user_id")
    private Utilisateurs utilisateur;
}
