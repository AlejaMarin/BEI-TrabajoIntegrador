package TrabajoIntegrador.MarinAlejandra.Controller;

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
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping("/modificar")
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.modificar(paciente));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Paciente eliminado correctamente");
    }
}