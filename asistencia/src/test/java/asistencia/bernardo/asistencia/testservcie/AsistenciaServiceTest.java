package asistencia.bernardo.asistencia.testservcie;

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

import asistencia.bernardo.asistencia.model.Asistencia;
import asistencia.bernardo.asistencia.repository.AsistenciaRepository;
import asistencia.bernardo.asistencia.service.AsistenciaService;

public class AsistenciaServiceTest {

    // 1. mock
    @Mock
    private AsistenciaRepository asistenciaRepository;

    // 2.mock
    @InjectMocks
    private AsistenciaService asistenciaService;

    // 3. mock
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- PRUEBA 1: obtener
    @Test
    public void testObtenerTodas() {
        // A. Preparar datos falsos
        List<Asistencia> listaMocks = new ArrayList<>();
        Asistencia a1 = new Asistencia();
        a1.setIdAsistencia(1L);
        a1.setEstado("P");
        
        Asistencia a2 = new Asistencia();
        a2.setIdAsistencia(2L);
        a2.setEstado("A");
        
        listaMocks.add(a1);
        listaMocks.add(a2);

        // B. mock
        when(asistenciaRepository.findAll()).thenReturn(listaMocks);

        // C. Ejecutar obtener 
        List<Asistencia> resultado = asistenciaService.obtenerTodas();

        // D. Verificar que funcionó bien
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Debería traer 2 registros
        verify(asistenciaRepository, times(1)).findAll();
    }

    // --- PRUEBA 2:guardar
    @Test
    public void testGuardar() {
        // A. Preparar un objeto Asistencia
        Asistencia nuevaAsistencia = new Asistencia();
        nuevaAsistencia.setIdAsistencia(1L);
        nuevaAsistencia.setEstado("P");
        nuevaAsistencia.setIdEstudiante(100L);

        // B. simular guardar
        when(asistenciaRepository.save(nuevaAsistencia)).thenReturn(nuevaAsistencia);

        // C. Ejecutar
        Asistencia resultado = asistenciaService.guardar(nuevaAsistencia);

        // D. Verificar
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdAsistencia());
        verify(asistenciaRepository, times(1)).save(nuevaAsistencia);
    }

    // --- PRUEBA 3: buscar
    @Test
    public void testBuscarPorEstudiante() {
        // A. Preparar datos
        Long idEstudianteBuscado = 100L;
        List<Asistencia> listaMocks = new ArrayList<>();
        Asistencia a1 = new Asistencia();
        a1.setIdAsistencia(1L);
        a1.setIdEstudiante(idEstudianteBuscado);
        listaMocks.add(a1);

        // B. Simular repositorio usando el Custom Query de tu código
        when(asistenciaRepository.findByIdEstudiante(idEstudianteBuscado)).thenReturn(listaMocks);

        // C. Ejecutar
        List<Asistencia> resultado = asistenciaService.buscarPorEstudiante(idEstudianteBuscado);

        // D. Verificar
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(100L, resultado.get(0).getIdEstudiante());
        verify(asistenciaRepository, times(1)).findByIdEstudiante(idEstudianteBuscado);
    }
}