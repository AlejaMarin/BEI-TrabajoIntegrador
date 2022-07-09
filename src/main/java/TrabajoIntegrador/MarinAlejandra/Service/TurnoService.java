package TrabajoIntegrador.MarinAlejandra.Service;

import TrabajoIntegrador.MarinAlejandra.Model.Turno;
import TrabajoIntegrador.MarinAlejandra.Repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno buscar(Long id) {
        logger.debug("Buscando turno con id" + id);
        return turnoRepository.findById(id).orElseThrow(null);
    }

    public Turno guardar(Turno turno) {
        logger.debug("Generando turno: " + turno);
        return turnoRepository.save(turno);
    }

    public Turno modificar(Turno turno) {
        Turno turnoUpdate = buscar(turno.getId());
        if (turnoUpdate != null) {
            logger.debug("Modificando turno con id: " + turno.getId());
            turnoUpdate.setPaciente(turno.getPaciente());
            turnoUpdate.setOdontologo(turno.getOdontologo());
            turnoUpdate.setFechaHora(turno.getFechaHora());
            turnoRepository.save(turnoUpdate);
        }
        return turnoUpdate;
    }

    public void eliminar(Long id) {
        Turno turnoDelete = buscar(id);
        if (turnoDelete != null) {
            logger.debug("Eliminando turno con id: " + id);
            turnoRepository.deleteById(id);
        } else {
            logger.debug("No existe un turno con id: " + id);
        }
    }

    public List<Turno> listarTurnos() {
        logger.debug("Listando todos los Turnos");
        return turnoRepository.findAll();
    }
}
