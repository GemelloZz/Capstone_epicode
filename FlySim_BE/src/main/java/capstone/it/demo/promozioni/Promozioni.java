package capstone.it.demo.promozioni;


import capstone.it.demo.auth.AppUser;
import capstone.it.demo.sim.Sim;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "promozioni")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Promozioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String nome;
    @NotBlank
    private String descrizione;
    @NotNull
    private int minuti;
    @NotNull
    private int giga ;
    @NotNull
    private Double prezzo ;
    @NotNull
    private int messaggi;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private PromozioniEnum promozioniEnum;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private AppUser appUser;





}
