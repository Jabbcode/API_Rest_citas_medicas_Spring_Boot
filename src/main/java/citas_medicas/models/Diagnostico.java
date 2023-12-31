package citas_medicas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valoracion_especialista")
    private String valoracion_especialista;

    @Column(name = "enfermedad")
    private String enfermedad;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
}