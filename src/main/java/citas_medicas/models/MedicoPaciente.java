package citas_medicas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos_pacientes")
public class MedicoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
