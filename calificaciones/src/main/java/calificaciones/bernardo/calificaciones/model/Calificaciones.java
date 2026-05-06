package calificaciones.bernardo.calificaciones.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CALIFICACIONES")
public class Calificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @Column(name = "VALOR_NOTA", columnDefinition = "NUMBER(3,1)")
    private Double valorNota;

    @Column(name = "ID_ESTUDIANTE")
    private Long idEstudiante;

    @Column(name = "ID_EVALUACION")
    private Long idEvaluacion;

    
}

