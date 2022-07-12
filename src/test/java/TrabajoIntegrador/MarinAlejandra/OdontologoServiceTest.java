package TrabajoIntegrador.MarinAlejandra;

import TrabajoIntegrador.MarinAlejandra.Exceptions.BadRequestException;
import TrabajoIntegrador.MarinAlejandra.Model.Odontologo;
import TrabajoIntegrador.MarinAlejandra.Service.OdontologoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Before
    public void cargarDataSet() {
        odontologoService.guardar(new Odontologo("Smith", "John", "ABC123"));
        odontologoService.guardar(new Odontologo("Redfield", "Chris", "XYZ987"));
    }

    @Test
    public void guardarYBuscarOdontologo() throws BadRequestException {
        Odontologo odontologo = odontologoService.guardar(new Odontologo("Bruce", "Wayne", "BAT001"));
        Assert.assertNotNull(odontologoService.buscar(odontologo.getId()));
    }

    @Test
    public void eliminarOdontologo() throws BadRequestException {
        odontologoService.eliminar(1L);
        try {
            Assert.assertTrue(odontologoService.buscar(1L) == null);
        } catch (BadRequestException e) {
            Assert.assertEquals("Odontologo con id: 1, no existe", e.getMessage());
        }
    }

    @Test
    public void listarTodos() {
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);
        System.out.println(odontologos);
    }
}