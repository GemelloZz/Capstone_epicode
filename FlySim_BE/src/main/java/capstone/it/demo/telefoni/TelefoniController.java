package capstone.it.demo.telefoni;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/telefoni")
@RequiredArgsConstructor
public class TelefoniController {

    private final TelefoniService telefonoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Telefoni> getAllTelefoni() {
        return telefonoService.getAllTelefoni();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Telefoni getTelefoniById(@PathVariable Long id) {
        return telefonoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefono non trovato con ID: " + id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telefoni createTelefoni(@RequestBody Telefoni telefoni) {
        return telefonoService.createTelefoni(telefoni);
    }


    @PostMapping("/with-image")
    @ResponseStatus(HttpStatus.CREATED)
    public Telefoni createTelefoniWithImage(@RequestParam("telefoni") String telefoniJson,
                                            @RequestParam("image") MultipartFile imageFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Telefoni telefoni = objectMapper.readValue(telefoniJson, Telefoni.class);


        return telefonoService.createTelefoni(telefoni, imageFile);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTelefoni(@PathVariable Long id) {
        boolean exists = telefonoService.findById(id).isPresent();
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefono non trovato con ID: " + id);
        }
        telefonoService.deleteTelefoni(id);
    }
}
