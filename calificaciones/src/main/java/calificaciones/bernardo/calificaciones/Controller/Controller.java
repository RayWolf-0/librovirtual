package calificaciones.bernardo.calificaciones.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import calificaciones.bernardo.calificaciones.Service.CalificacionesService;
import calificaciones.bernardo.calificaciones.model.Calificaciones;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private CalificacionesService calificacionesService;

    @GetMapping("/listar")
    public List<Calificaciones> listarTodas() {
        return calificacionesService.obtenerTodas();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNota(@RequestBody Calificaciones nuevaNota) {
        try {
            Calificaciones notaGuardada = calificacionesService.guardarCalificacion(nuevaNota);
            return ResponseEntity.ok("Calificación registrada y evento notificado al apoderado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en la persistencia operativa.");
        }
    }
}
