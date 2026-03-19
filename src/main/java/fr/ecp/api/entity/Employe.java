package fr.ecp.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "employe")
@Data
@NoArgsConstructor
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no_secu", length = 13)
    private String noSecu;

    @Column(name = "date_emp")
    private LocalDate dateEmp;

    @OneToOne
    @JoinColumn(name = "id_user_id")
    private Utilisateurs idUser;
}
