package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Repository.CalificacionesRepository;
import model.Calificaciones;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*") // Eficiencia técnica para conectar con React
public class Controller {

    @Autowired
    private CalificacionesRepository repository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNota(@RequestBody Calificaciones nuevaNota) {
        
        try {
            Calificaciones notaGuardada = repository.save(nuevaNota);
            
            emitirEventoNotaRegistrada(notaGuardada);
            
            return ResponseEntity.ok("Calificación registrada y evento notificado al apoderado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en la persistencia operativa.");
        }
    }

    private void emitirEventoNotaRegistrada(Calificaciones nota) {
        System.out.println("EVENTO EMITIDO: Nueva nota " + nota.getValorNota() + 
                           " para el estudiante " + nota.getIdEstudiante());
    }
}

