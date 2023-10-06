package citas_medicas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fecha_hora;

    @Column(name = "motivo_cita")
    private String motivo_cita;

    @JsonManagedReference
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_diagnostico")  // Esta es la parte clave
    private Diagnostico diagnostico;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
}
