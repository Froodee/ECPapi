package fr.ecp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ref_pds")
    private Short refPds;

    @Column(name = "qtepds")
    private Short qtePds;

    @Column(name = "prix_pds", precision = 10, scale = 2)
    private BigDecimal prixPds;

    @Column(name = "lib_pds", length = 32)
    private String libPds;

    @Column(name = "desc_pds", length = 512)
    private String descPds;

    @Column(name = "design_pds", length = 255)
    private String designPds;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_promo_id")
    private Promo idPromo;

    @ManyToOne
    @JoinColumn(name = "code_typ_id")
    private TypeProduit codeTyp;

    @JsonIgnore
    @OneToMany(mappedBy = "refPds")
    private List<Avis> avis;
}
