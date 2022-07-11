package TrabajoIntegrador.MarinAlejandra.Security;

import TrabajoIntegrador.MarinAlejandra.Model.Rol;
import TrabajoIntegrador.MarinAlejandra.Model.Usuario;
import TrabajoIntegrador.MarinAlejandra.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("contraseniaAdmin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("contraseniaUser");
        usuarioRepository.save(new Usuario("Admin1", "admin1", "admin1@mail.com", hashedPassword, Rol.ADMIN));
        usuarioRepository.save(new Usuario("User1", "user1", "user1@mail.com", hashedPassword2, Rol.USER));
    }
}