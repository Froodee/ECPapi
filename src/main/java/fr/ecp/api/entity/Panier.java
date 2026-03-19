package fr.ecp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "panier")
@Data
@NoArgsConstructor
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "num_cde_id")
    private Commande commande;

    @JsonIgnore
    @OneToMany(mappedBy = "panier")
    private List<Produits> produits;
}
