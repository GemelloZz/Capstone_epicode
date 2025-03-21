package capstone.it.demo.telefoni;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoniRepository extends JpaRepository < Telefoni, Long> {

    public Telefoni findByNumeroSeriale(int numeroSeriale);
    public Telefoni findByMarca(String marca);
}
