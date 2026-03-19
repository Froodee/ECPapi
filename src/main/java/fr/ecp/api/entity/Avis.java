package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "avis")
@Data
@NoArgsConstructor
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "commentaire", length = 500)
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "ref_pds_id")
    private Produits refPds;

    @ManyToOne
    @JoinColumn(name = "id_user_id")
    private Utilisateurs idUser;
}
