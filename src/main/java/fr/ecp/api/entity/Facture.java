package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "facture")
@Data
@NoArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_fact")
    private Short numFact;

    @Column(name = "date_fact")
    private LocalDate dateFact;

    @OneToOne
    @JoinColumn(name = "num_cde_id")
    private Commande commande;
}
