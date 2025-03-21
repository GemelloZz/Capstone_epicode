package capstone.it.demo.sim;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sim")
@RequiredArgsConstructor
public class SimController {

    private final SimService simService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Sim> getAllSim() {

        return simService.getAllSim();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Sim> getSimById(@PathVariable Long id) {

        return simService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SimResponse createSim(@RequestBody Sim sim) {

        return simService.createSim(sim);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSim(@PathVariable Long id) {

    simService.deleteSim(id);
    }

}
