package fr.ecp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_user")
    private Short idUser;

    @Column(name = "nom_user", length = 64)
    private String nomUser;

    @Column(name = "adr_user", length = 64)
    private String adrUser;

    @Column(name = "cp_user", length = 5)
    private String cpUser;

    @Column(name = "ville_user", length = 64)
    private String villeUser;

    @Column(name = "num_user", length = 10)
    private String numUser;

    @Column(name = "email_user", length = 255)
    private String emailUser;

    @JsonIgnore
    @OneToOne(mappedBy = "idUser", cascade = CascadeType.ALL)
    private Admin admin;

    @JsonIgnore
    @OneToOne(mappedBy = "idUser", cascade = CascadeType.ALL)
    private Client client;

    @JsonIgnore
    @OneToOne(mappedBy = "idUser", cascade = CascadeType.ALL)
    private Employe employe;

    @JsonIgnore
    @OneToMany(mappedBy = "idUser")
    private List<Avis> avis;

    @JsonIgnore
    @OneToMany(mappedBy = "idUser")
    private List<Commande> commandes;
}
