package asistencia.bernardo.asistencia.controller;

import asistencia.bernardo.asistencia.model.Asistencia;
import asistencia.bernardo.asistencia.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Asistencia>> listarTodas() {
        List<Asistencia> asistencias = asistenciaService.obtenerTodas();
        return new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Asistencia> registrarAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.guardar(asistencia);
        return new ResponseEntity<>(nuevaAsistencia, HttpStatus.CREATED);
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Asistencia>> listarPorEstudiante(@PathVariable Long id) {
        List<Asistencia> asistencias = asistenciaService.buscarPorEstudiante(id);
        return new ResponseEntity<>(asistencias, HttpStatus.OK);
    }
}