package TrabajoIntegrador.MarinAlejandra.Repository;

import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}