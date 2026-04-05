package usuarios.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usuarios.models.Usuario;
import usuarios.Repository.usuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;

    // Obtener usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar un usuario por id
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Guardar o actualizar usuarios
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public boolean delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Usuario> login(String correo, String contrasenaVal) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getCorreo().equals(correo) && 
                             u.getContrasenaVal().equals(contrasenaVal))
                .findFirst();
    }
}