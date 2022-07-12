package TrabajoIntegrador.MarinAlejandra;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Domicilio;
import TrabajoIntegrador.MarinAlejandra.Model.Paciente;
import TrabajoIntegrador.MarinAlejandra.Service.PacienteService;
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
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Before
    public void cargarDataSet() {
        pacienteService.guardar(new Paciente("Alexander", "Volkov", new Domicilio("Calle123", "1A", "Localidad1", "Provincia1"), 123456, LocalDate.of(2022, 7, 11)));
        pacienteService.guardar(new Paciente("Tom", "Hardy", new Domicilio("Calle456", "1B", "Localidad2", "Provincia2"), 789654, LocalDate.of(2022, 6, 10)));
    }

    @Test
    public void guardarYBuscarPaciente() throws BadRequestException {
        Paciente paciente = pacienteService.guardar(new Paciente("Emma", "Stone", new Domicilio("Calle753", "3A", "Localidad3", "Provincia3"), 147369, LocalDate.of(2022, 7, 7)));
        Assert.assertNotNull(pacienteService.buscar(paciente.getId()));
    }

    @Test
    public void eliminarPaciente() throws BadRequestException {
        pacienteService.eliminar(1L);
        try {
            Assert.assertTrue(pacienteService.buscar(1L) == null);
        } catch (BadRequestException e) {
            Assert.assertEquals("Paciente con id: 1, no existe", e.getMessage());
        }
    }

    @Test
    public void listarTodos() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }
}