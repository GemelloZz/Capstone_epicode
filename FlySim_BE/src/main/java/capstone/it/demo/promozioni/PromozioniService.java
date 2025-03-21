package capstone.it.demo.promozioni;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromozioniService {
    private final PromozioniRepository promozioniRepository;

    public Iterable<Promozioni> getAllPromozioni() {
        Iterable<Promozioni> promozioni = promozioniRepository.findAll();
        return promozioni;
    }

    public Optional<Promozioni> findById(Long id) {
        return promozioniRepository.findById(id);
    }


    public Promozioni createPromozioni(Promozioni promozioni) {
        return promozioniRepository.save(promozioni);
    }

    public void deletePromozioni(Long id) {
        promozioniRepository.deleteById((long) Math.toIntExact(id));
    }
}
