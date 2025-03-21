package capstone.it.demo.telefoni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoniResponse {
    private String Marca;
    private String Modello;
    private int NumeroSeriale;
    private int Memoria;
    private String img;
}
