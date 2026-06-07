package calificaciones.bernardo.calificaciones.testservcie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import calificaciones.bernardo.calificaciones.Repository.CalificacionesRepository;
import calificaciones.bernardo.calificaciones.Service.CalificacionesService;
import calificaciones.bernardo.calificaciones.model.Calificaciones;

public class CalificacionesServiceTest {

    // Mockeamos el repositorio para no pegarle a la BD de Oracle en las pruebas
    @Mock
    private CalificacionesRepository repository;

    // Inyectamos los mocks en el servicio que vamos a probar
    @InjectMocks
    private CalificacionesService calificacionesService;

    // Inicializamos los mocks antes de correr cada test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodas() {
        // Preparamos los datos de prueba
        List<Calificaciones> listaMocks = new ArrayList<>();
        
        Calificaciones nota1 = new Calificaciones();
        nota1.setIdCalificacion(1L);
        nota1.setValorNota(6.5);
        nota1.setIdEstudiante(100L);
        nota1.setIdEvaluacion(10L);

        Calificaciones nota2 = new Calificaciones();
        nota2.setIdCalificacion(2L);
        nota2.setValorNota(5.0);
        nota2.setIdEstudiante(101L);
        nota2.setIdEvaluacion(10L);

        listaMocks.add(nota1);
        listaMocks.add(nota2);

        // Definimos el comportamiento esperado del mock
        when(repository.findAll()).thenReturn(listaMocks);

        // Ejecutamos el método del servicio
        List<Calificaciones> resultado = calificacionesService.obtenerTodas();

        // Verificamos que el resultado sea correcto y que el repositorio se haya consultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGuardarCalificacion() {
        // Creamos la calificación de prueba
        Calificaciones nuevaNota = new Calificaciones();
        nuevaNota.setIdCalificacion(1L);
        nuevaNota.setValorNota(7.0);
        nuevaNota.setIdEstudiante(102L);
        nuevaNota.setIdEvaluacion(15L);

        // Simulamos la respuesta del save en el repositorio
        when(repository.save(nuevaNota)).thenReturn(nuevaNota);

        // Ejecutamos el guardado
        Calificaciones resultado = calificacionesService.guardarCalificacion(nuevaNota);

        // Validamos que los datos guardados coincidan
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCalificacion());
        assertEquals(7.0, resultado.getValorNota());
        assertEquals(102L, resultado.getIdEstudiante());
        
        // Verificamos que el método save se llamó exactamente una vez
        verify(repository, times(1)).save(nuevaNota);
    }
}