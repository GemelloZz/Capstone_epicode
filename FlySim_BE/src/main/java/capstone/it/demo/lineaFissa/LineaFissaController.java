package capstone.it.demo.lineaFissa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lineaFissa")
@RequiredArgsConstructor
public class LineaFissaController {

    private final LineaFissaService lineaFissaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<LineaFissa> getAllLineaFissa() {
        return lineaFissaService.getAllLineaFissa();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public Optional<LineaFissa> getLineaFissaById(@PathVariable Long id) {
        return lineaFissaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LineaFissa createLineaFissa(@RequestBody LineaFissa lineaFissa) {
        return lineaFissaService.createLineaFissa(lineaFissa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLineaFissa(@PathVariable Long id) {

        lineaFissaService.deleteLineaFissa(id);
    }
}
