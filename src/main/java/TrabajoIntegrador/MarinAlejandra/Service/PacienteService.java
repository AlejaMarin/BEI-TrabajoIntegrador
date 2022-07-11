package TrabajoIntegrador.MarinAlejandra.Service;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import TrabajoIntegrador.MarinAlejandra.Repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente buscar(Long id) throws BadRequestException {
        logger.debug("Buscando paciente con id: " + id);
        Optional<Paciente> encontrado = pacienteRepository.findById(id);
        if (encontrado.isPresent()) {
            return mapper.convertValue(encontrado, Paciente.class);
        } else {
            throw new BadRequestException("Paciente con id: " + id + ", no existe");
        }
    }

    public Paciente guardar(Paciente paciente) {
        logger.debug("Guardando paciente: " + paciente);
        return pacienteRepository.save(paciente);
    }

    public Paciente modificar(Paciente paciente) throws BadRequestException {
        Paciente pacienteUpdate = buscar(paciente.getId());
        if (pacienteUpdate != null) {
            logger.debug("Modificando paciente con id: " + paciente.getId());
            pacienteUpdate.setNombre(paciente.getNombre());
            pacienteUpdate.setApellido(paciente.getApellido());
            pacienteUpdate.setDni(paciente.getDni());
            pacienteUpdate.setFechaAlta(paciente.getFechaAlta());
            pacienteUpdate.setDomicilio(paciente.getDomicilio());
            pacienteRepository.save(pacienteUpdate);
        }
        return pacienteUpdate;
    }

    public void eliminar(Long id) throws BadRequestException {
        Paciente pacienteDelete = buscar(id);
        if (pacienteDelete != null) {
            logger.debug("Eliminando paciente con id: " + id);
            pacienteRepository.deleteById(id);
        } else {
            logger.debug("No existe un paciente con id: " + id);
        }
    }

    public List<Paciente> listarPacientes() {
        logger.debug("Listando todos los Pacientes");
        return pacienteRepository.findAll();
    }
}
