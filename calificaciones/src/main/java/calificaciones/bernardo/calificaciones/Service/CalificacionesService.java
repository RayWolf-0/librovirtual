package calificaciones.bernardo.calificaciones.Service;

import calificaciones.bernardo.calificaciones.Repository.CalificacionesRepository;
import calificaciones.bernardo.calificaciones.model.Calificaciones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionesService {

    @Autowired
    private CalificacionesRepository repository;

    public Calificaciones guardarCalificacion(Calificaciones nota) {
        Calificaciones resultado = repository.save(nota);
        notificarEventoAsincrono(resultado);
        return resultado;
    }

    public List<Calificaciones> obtenerTodas() {
        return repository.findAll();
    }

    private void notificarEventoAsincrono(Calificaciones nota) {
        System.out.println("Publicando evento: Estudiante " + nota.getIdEstudiante() + " tiene nueva nota.");
    }
}