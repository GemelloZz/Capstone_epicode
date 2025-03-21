package capstone.it.demo.promozioniLineaFissa;

import capstone.it.demo.lineaFissa.LineaFissa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promozioni_linea_fissa")
public class PromozioniLineaFissa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String nome;
    @NotBlank
    private String descrizione;
    @NotNull
    private String minuti;
    @NotNull
    private String giga ;
    @NotBlank
    private Double prezzo;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private PromozioniLineaFissaEnum promozioniLineaFissaEnum;





}
