package citas_medicas.services;

import citas_medicas.models.Medico;
import citas_medicas.models.Paciente;
import citas_medicas.models.Usuario;
import citas_medicas.repositories.PacienteRepository;
import citas_medicas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String LoginService(String username, String clave) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verificar la contraseña
            if (usuario.getClave().equals(clave)) {
                if (usuario instanceof Medico) {
                    return "¡Bienvenido Médico " + usuario.getNombre() + " " + usuario.getApellido() + "!";
                } else if (usuario instanceof Paciente) {
                    return "¡Bienvenido Paciente " + usuario.getNombre() + " " + usuario.getApellido() + "!";
                }
            } else {
                return "Credenciales invalidas.";
            }
        } else {
            return "Credenciales invalidas.";
        }

        return "Error de autenticación.";
    }
}