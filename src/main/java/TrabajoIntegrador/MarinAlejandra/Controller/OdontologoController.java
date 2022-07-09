package TrabajoIntegrador.MarinAlejandra.Controller;

import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import TrabajoIntegrador.MarinAlejandra.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping("/modificar")
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.modificar(odontologo));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Odontologo eliminado correctamente");
    }
}
