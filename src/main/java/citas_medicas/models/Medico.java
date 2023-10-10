package citas_medicas.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicos")
@DiscriminatorValue("MEDICO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Medico extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_colegiado")
    private String num_colegiado;

    @JsonManagedReference(value = "medicoReference")
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Cita> citas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medico_paciente",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_paciente")
    )
    private Set<Paciente> pacientes = new HashSet<>();

    // Métodos para asignar un paciente a este médico
    public void asignarPaciente(Paciente paciente) {
        this.pacientes.add(paciente);
        paciente.getMedicos().add(this);
    }

    public void eliminarPaciente(Paciente paciente) {
        this.pacientes.remove(paciente);
        paciente.getMedicos().remove(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, num_colegiado);
    }

}
