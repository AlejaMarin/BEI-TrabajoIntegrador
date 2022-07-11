package TrabajoIntegrador.MarinAlejandra.Controller;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import TrabajoIntegrador.MarinAlejandra.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping("/modificar")
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.modificar(paciente));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws BadRequestException {
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Paciente eliminado correctamente");
    }
}