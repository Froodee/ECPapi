package fr.ecp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "commande")
@Data
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_cde")
    private Short numCde;

    @Column(name = "date_cde")
    private LocalDate dateCde;

    @ManyToOne
    @JoinColumn(name = "id_user_id")
    private Utilisateurs idUser;

    @JsonIgnore
    @OneToOne(mappedBy = "commande")
    private Facture facture;

    @JsonIgnore
    @OneToOne(mappedBy = "commande")
    private Panier panier;
}
