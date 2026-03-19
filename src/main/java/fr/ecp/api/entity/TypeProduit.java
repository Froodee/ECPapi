package fr.ecp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "type_produit")
@Data
@NoArgsConstructor
public class TypeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_typ")
    private Short codeTyp;

    @Column(name = "lib_typ", length = 255)
    private String libTyp;

    @JsonIgnore
    @OneToMany(mappedBy = "codeTyp")
    private List<Produits> produits;
}
