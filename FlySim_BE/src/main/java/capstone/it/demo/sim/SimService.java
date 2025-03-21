package capstone.it.demo.sim;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimService {

    private final SimRepository simRepository;

    public Iterable<Sim> getAllSim() {
        Iterable<Sim> sim = simRepository.findAll();
        if (sim != null) {
            return sim;
        } else {
            return null;
    }
    }

    public Optional<Sim> findById(Long id) {
        Optional<Sim> sim = simRepository.findById(id);
        return sim;
    }

    public SimResponse createSim(Sim sim) {
       Sim savedsim = simRepository.save(sim);
        return  new SimResponse(savedsim.getId(), savedsim.getNumber(), savedsim.getProvider());
    }

    public void deleteSim(Long id) {
        simRepository.deleteById(id);
    }
}
