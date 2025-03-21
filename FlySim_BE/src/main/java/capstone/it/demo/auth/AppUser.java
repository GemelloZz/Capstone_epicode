package capstone.it.demo.auth;

import capstone.it.demo.promozioni.Promozioni;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "telefono",  unique = true)
    private String telefono;

    @Column(name = "telefono_linea_fissa",  unique = true)
    private String telefonoLineaFissa;

    @Column(name = "Città")
    private String Città;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "cap")
    private String cap;

    @Column(nullable = true)
    private String avatar;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Promozioni> promozioni = new HashSet<>();

}
