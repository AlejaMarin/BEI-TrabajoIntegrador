package TrabajoIntegrador.MarinAlejandra.Service;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import TrabajoIntegrador.MarinAlejandra.Model.Turno;
import TrabajoIntegrador.MarinAlejandra.Repository.OdontologoRepository;
import TrabajoIntegrador.MarinAlejandra.Repository.PacienteRepository;
import TrabajoIntegrador.MarinAlejandra.Repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }

    public Turno buscar(Long id) throws BadRequestException {
        logger.debug("Buscando turno con id: " + id);
        Optional<Turno> encontrado = turnoRepository.findById(id);
        if (encontrado.isPresent()) {
            return mapper.convertValue(encontrado, Turno.class);
        } else {
            throw new BadRequestException("Turno con id: " + id + ", no existe");
        }
    }

    public Turno guardar(Turno turno) {
        logger.debug("Generando turno: " + turno);
        return turnoRepository.save(turno);
    }

    public Turno modificar(Turno turno) throws BadRequestException {
        Turno turnoUpdate = buscar(turno.getId());
        Paciente pacienteExistente = pacienteRepository.findById(turnoUpdate.getPaciente().getId()).orElseThrow(null);
        Odontologo odontologoExistente = odontologoRepository.findById(turnoUpdate.getOdontologo().getId()).orElseThrow(null);
        if (turnoUpdate != null && pacienteExistente != null && odontologoExistente != null) {
            logger.debug("Modificando turno con id: " + turno.getId());
            turnoUpdate.setPaciente(pacienteExistente);
            turnoUpdate.setOdontologo(odontologoExistente);
            turnoUpdate.setFechaHora(turno.getFechaHora());
            turnoRepository.save(turnoUpdate);
        }
        return turnoUpdate;
    }

    public void eliminar(Long id) throws BadRequestException {
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
