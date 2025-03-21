package capstone.it.demo.auth;

import capstone.it.demo.promozioni.Promozioni;
import capstone.it.demo.promozioni.PromozioniRepository;
import capstone.it.demo.promozioni.PromozioniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final PromozioniService promozioniService; // Servizio per gestire le promozioni

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail(), registerRequest.getTelefonoLineaFissa(), registerRequest.getCap(), registerRequest.getTelefono(), registerRequest.getCitta(), registerRequest.getIndirizzo(), registerRequest.getRoles() != null ? registerRequest.getRoles() : Set.of(Role.ROLE_USER));
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @GetMapping("/user")
    public ResponseEntity<AppUser> getAuthenticatedUser(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        AppUser user = appUserService.getUserByToken(token);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/user/{username}/promozioni/{promozioneId}")
    public ResponseEntity<String> addPromozioneToUser(@PathVariable String username, @PathVariable Long promozioneId) {
        try {
            appUserService.addPromozioneToUser(username, promozioneId);
            return ResponseEntity.ok("Promozione aggiunta con successo all'utente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante l'aggiunta della promozione: " + e.getMessage());
        }
    }

    @GetMapping("/user/{username}/promozioni")
    public ResponseEntity<Set<Promozioni>> getPromozioniByUser(@PathVariable String username) {
        AppUser user = appUserService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user.getPromozioni());
    }


}















