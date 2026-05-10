package asistencia.bernardo.asistencia.model;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name = "ASISTENCIA")
public class Asistencia {

    @Id
    @Column(name = "ID_ASISTENCIA")
    private Long idAsistencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "ESTADO", columnDefinition = "CHAR(1)")
    private String estado; // P = Presente, A = Ausente

    @Column(name = "ID_ESTUDIANTE", nullable = false)
    private Long idEstudiante;

    @Column(name = "ID_ASIGNATURA", nullable = false)
    private Long idAsignatura;

}