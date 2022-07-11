package TrabajoIntegrador.MarinAlejandra.Controller;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Turno;
import TrabajoIntegrador.MarinAlejandra.Service.OdontologoService;
import TrabajoIntegrador.MarinAlejandra.Service.PacienteService;
import TrabajoIntegrador.MarinAlejandra.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Turno>> listarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> generarTurno(@RequestBody Turno turno) throws BadRequestException {
        ResponseEntity<Turno> response;
        if (odontologoService.buscar(turno.getOdontologo().getId()) != null && pacienteService.buscar(turno.getPaciente().getId()) != null) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/modificar")
    public ResponseEntity<Turno> modificarTurno(@RequestBody Turno turno) throws BadRequestException {
        return ResponseEntity.ok(turnoService.modificar(turno));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws BadRequestException {
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado correctamente");
    }
}
