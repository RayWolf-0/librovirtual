package calificaciones.bernardo.calificaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import calificaciones.bernardo.calificaciones.model.Calificaciones;

public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long>{
    
}
