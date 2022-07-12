package TrabajoIntegrador.MarinAlejandra;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Domicilio;
import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import TrabajoIntegrador.MarinAlejandra.Model.Turno;
import TrabajoIntegrador.MarinAlejandra.Service.OdontologoService;
import TrabajoIntegrador.MarinAlejandra.Service.PacienteService;
import TrabajoIntegrador.MarinAlejandra.Service.TurnoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

    @Before
    public void cargarDataSet() {
        Paciente p1 = pacienteService.guardar(new Paciente("Alexander", "Volkov", new Domicilio("Calle123", "1A", "Localidad1", "Provincia1"), 123456, LocalDate.of(2022, 7, 11)));
        Odontologo o1 = odontologoService.guardar(new Odontologo("Smith", "John", "ABC123"));
        Paciente p2 = pacienteService.guardar(new Paciente("Zoe", "Kravitz", new Domicilio("CalleFalsa", "1A", "Localidad0", "Provincia10"), 367149, LocalDate.of(2022, 7, 9)));
        Odontologo o2 = odontologoService.guardar(new Odontologo("Smith", "John", "ABC123"));
        Turno t = turnoService.guardar(new Turno(p1, o1, LocalDateTime.of(2022, 7, 15, 11, 20, 0)));
    }

    @Test
    public void guardarYBuscarTurno() throws BadRequestException {
        this.cargarDataSet();
        Paciente p = pacienteService.buscar(2L);
        Odontologo o = odontologoService.buscar(2L);
        Turno t = turnoService.guardar(new Turno(p, o, LocalDateTime.of(2022, 7, 12, 11, 20, 0)));
        Assert.assertNotNull(turnoService.buscar(t.getId()));
    }

    @Test
    public void eliminarTurno() throws BadRequestException {
        turnoService.eliminar(1L);
        try {
            Assert.assertTrue(turnoService.buscar(1L) == null);
        } catch (BadRequestException e) {
            Assert.assertEquals("Turno con id: 1, no existe", e.getMessage());
        }
    }

    @Test
    public void listarTodos() {
        List<Turno> turnos = turnoService.listarTurnos();
        Assert.assertTrue(!turnos.isEmpty());
        Assert.assertTrue(turnos.size() > 0);
        System.out.println(turnos);
    }
}