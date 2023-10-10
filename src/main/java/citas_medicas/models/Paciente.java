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
@Table(name = "pacientes")
@DiscriminatorValue("PACIENTE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Paciente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nss")
    private String nss;

    @Column(name = "num_tarjeta")
    private String num_tarjeta;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @JsonManagedReference(value = "pacienteReference")
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medico_paciente",
            joinColumns = @JoinColumn(name = "id_paciente"),
            inverseJoinColumns = @JoinColumn(name = "id_medico")
    )
    private Set<Medico> medicos = new HashSet<>();

    public void asignarMedico(Medico medico) {
        this.medicos.add(medico);
        medico.getPacientes().add(this);
    }

    public void eliminarMedico(Medico medico) {
        this.medicos.remove(medico);
        medico.getPacientes().remove(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nss, num_tarjeta, telefono, direccion);
    }

}
