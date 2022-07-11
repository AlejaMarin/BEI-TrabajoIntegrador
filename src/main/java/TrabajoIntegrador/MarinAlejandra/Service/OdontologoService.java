package TrabajoIntegrador.MarinAlejandra.Service;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import TrabajoIntegrador.MarinAlejandra.Repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo buscar(Long id) throws BadRequestException {
        logger.debug("Buscando odontologo con id: " + id);
        Optional<Odontologo> encontrado = odontologoRepository.findById(id);
        if (encontrado.isPresent()) {
            return mapper.convertValue(encontrado, Odontologo.class);
        } else {
            throw new BadRequestException("Odontologo con id: " + id + ", no existe");
        }
    }

    public Odontologo guardar(Odontologo odontologo) {
        logger.debug("Guardando odontologo: " + odontologo);
        return odontologoRepository.save(odontologo);
    }

    public Odontologo modificar(Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoUpdate = buscar(odontologo.getId());
        if (odontologoUpdate != null) {
            logger.debug("Modificando odontologo con id: " + odontologo.getId());
            odontologoUpdate.setApellido(odontologo.getApellido());
            odontologoUpdate.setNombre(odontologo.getNombre());
            odontologoUpdate.setMatricula(odontologo.getMatricula());
            odontologoRepository.save(odontologoUpdate);
        }
        return odontologoUpdate;
    }

    public void eliminar(Long id) throws BadRequestException {
        Odontologo odontologoDelete = buscar(id);
        if (odontologoDelete != null) {
            logger.debug("Eliminando odontologo con id: " + id);
            odontologoRepository.deleteById(id);
        } else {
            logger.debug("No existe un odontologo con id: " + id);
        }
    }

    public List<Odontologo> listarOdontologos() {
        logger.debug("Listando todos los Odontologos");
        return odontologoRepository.findAll();
    }
}
