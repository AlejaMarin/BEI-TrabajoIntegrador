package TrabajoIntegrador.MarinAlejandra.Repository;

import TrabajoIntegrador.MarinAlejandra.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}