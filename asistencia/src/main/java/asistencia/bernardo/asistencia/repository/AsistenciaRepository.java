package asistencia.bernardo.asistencia.repository;

import asistencia.bernardo.asistencia.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByIdEstudiante(Long idEstudiante);
}