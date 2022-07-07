package TrabajoIntegrador.MarinAlejandra.Repository;

import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}