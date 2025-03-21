package capstone.it.demo.sim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimResponse {
    private Long id;
    private int number;
    private String provider;

}
