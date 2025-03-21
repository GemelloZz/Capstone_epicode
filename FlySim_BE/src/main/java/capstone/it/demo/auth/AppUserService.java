package capstone.it.demo.auth;

import capstone.it.demo.promozioni.Promozioni;
import capstone.it.demo.promozioni.PromozioniRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserService {

    final private AppUserRepository appUserRepository;
    final private PromozioniRepository promozioniRepository; // Aggiungi il repository per le promozioni
    final private PasswordEncoder passwordEncoder;
    final private AuthenticationManager authenticationManager;
    final private JwtTokenUtil jwtTokenUtil;


    public AppUser registerUser(String username, String password, String firstName, String lastName, String email, String telefonoLineaFissa, String cap, String telefono, String citta, String indirizzo, Set<Role> roles) {

        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }
        if (appUserRepository.existsByEmail(email)) {
            throw new EntityExistsException("Email già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setRoles(roles);
        appUser.setTelefonoLineaFissa(telefonoLineaFissa);
        appUser.setCap(cap);
        appUser.setTelefono(telefono);
        appUser.setCittà(citta);
        appUser.setIndirizzo(indirizzo);


        return appUserRepository.save(appUser);
    }


    public AppUser getUserByToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return appUserRepository.findByUsername(username).orElse(null);
    }




    public String authenticateUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }

    public AppUser findById(Long userId) {
        return appUserRepository.findById(userId).orElse(null);
    }

    public void save(AppUser user) {
        appUserRepository.save(user);
    }


    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username).orElse(null);
    }

    public AppUser addPromozioneToUser(String username, Long promozioneId) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        Promozioni promozione = promozioniRepository.findById(promozioneId).orElseThrow(() -> new EntityNotFoundException("Promozione non trovata"));
        user.getPromozioni().add(promozione);
        return  appUserRepository.save(user);
    }
}
