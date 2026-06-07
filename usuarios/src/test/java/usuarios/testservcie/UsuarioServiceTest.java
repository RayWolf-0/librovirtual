package usuarios.testservcie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import usuarios.Repository.usuarioRepository;
import usuarios.Service.UsuarioService;
import usuarios.models.Usuario;

public class UsuarioServiceTest {

    // Mockeamos la BD de usuarios
    @Mock
    private usuarioRepository usuarioRepository;

    // Inyectamos el mock en tu servicio real
    @InjectMocks
    private UsuarioService usuarioService;

    // Inicializamos antes de cada test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Preparamos un par de usuarios falsos
        List<Usuario> listaMocks = new ArrayList<>();
        listaMocks.add(new Usuario(1L, "Lya Lopez", "lya@colegio.cl", "Admin", "1234"));
        listaMocks.add(new Usuario(2L, "Profe Roberto", "roberto@colegio.cl", "Docente", "abcd"));

        // Le decimos al repo qué devolver
        when(usuarioRepository.findAll()).thenReturn(listaMocks);

        // Probamos el método
        List<Usuario> resultado = usuarioService.findAll();

        // Validamos que traiga los 2 y que se haya llamado al repo
        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Preparamos un usuario
        Usuario mockUser = new Usuario(1L, "Lya Lopez", "lya@colegio.cl", "Admin", "1234");

        // Simulamos la búsqueda
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Ejecutamos
        Optional<Usuario> resultado = usuarioService.findById(1L);

        // Validamos que lo encontró y que el nombre coincide
        assertTrue(resultado.isPresent());
        assertEquals("Lya Lopez", resultado.get().getNombreCompleto());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        // Usuario a guardar
        Usuario nuevoUsuario = new Usuario(3L, "Nuevo Alumno", "nuevo@colegio.cl", "Estudiante", "pass");

        // Simulamos el save
        when(usuarioRepository.save(nuevoUsuario)).thenReturn(nuevoUsuario);

        // Guardamos
        Usuario resultado = usuarioService.save(nuevoUsuario);

        // Validamos
        assertEquals(3L, resultado.getIdUsuario());
        assertEquals("nuevo@colegio.cl", resultado.getCorreo());
        verify(usuarioRepository, times(1)).save(nuevoUsuario);
    }

    @Test
    public void testDelete_Exitoso() {
        Long idAEliminar = 1L;

        // Simulamos que el usuario sí existe en la BD
        when(usuarioRepository.existsById(idAEliminar)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(idAEliminar);

        // Probamos el método delete
        boolean resultado = usuarioService.delete(idAEliminar);

        // Validamos que devuelva true y que llamó a los métodos correctos
        assertTrue(resultado);
        verify(usuarioRepository, times(1)).existsById(idAEliminar);
        verify(usuarioRepository, times(1)).deleteById(idAEliminar);
    }

    @Test
    public void testLogin_Exitoso() {
        // Como tu login filtra sobre la lista completa de findAll(), mockeamos esa lista
        List<Usuario> listaMocks = new ArrayList<>();
        listaMocks.add(new Usuario(1L, "Lya Lopez", "lya@colegio.cl", "Admin", "1234"));
        
        when(usuarioRepository.findAll()).thenReturn(listaMocks);

        // Probamos mandar las credenciales correctas
        Optional<Usuario> resultado = usuarioService.login("lya@colegio.cl", "1234");

        // Validamos que el login funcionó (isPresent = true)
        assertTrue(resultado.isPresent());
        assertEquals("Admin", resultado.get().getRol());
    }

    @Test
    public void testLogin_Fallido() {
        // Mockeamos la misma lista
        List<Usuario> listaMocks = new ArrayList<>();
        listaMocks.add(new Usuario(1L, "Lya Lopez", "lya@colegio.cl", "Admin", "1234"));
        
        when(usuarioRepository.findAll()).thenReturn(listaMocks);

        // Probamos con una contraseña incorrecta
        Optional<Usuario> resultado = usuarioService.login("lya@colegio.cl", "contraseñaMala");

        // Validamos que el login rebotó (isPresent = false)
        assertFalse(resultado.isPresent());
    }
}