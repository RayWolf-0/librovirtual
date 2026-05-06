package Service;

import model.Calificaciones;
import Repository.CalificacionesRepository;
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

    private void notificarEventoAsincrono(Calificaciones nota) {
        System.out.println("Publicando evento: Estudiante " + nota.getIdEstudiante() + " tiene nueva nota.");
    }
}
