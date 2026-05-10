package asistencia.bernardo.asistencia.service;

import asistencia.bernardo.asistencia.model.Asistencia;
import asistencia.bernardo.asistencia.repository.AsistenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;
    public List<Asistencia> obtenerTodas() {
        return asistenciaRepository.findAll();
    }

    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> buscarPorEstudiante(Long idEstudiante) {
        return asistenciaRepository.findByIdEstudiante(idEstudiante);
    }
}