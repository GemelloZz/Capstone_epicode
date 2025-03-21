package capstone.it.demo.sim;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimRequest {

    @NotBlank(message="Campo obbligatorio")
    private int number;
    @NotNull(message="Campo obbligatorio")
    private String provider;
}
