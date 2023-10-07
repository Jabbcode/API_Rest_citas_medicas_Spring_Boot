package citas_medicas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_colegiado")
    private String num_colegiado;

    @JsonManagedReference(value = "medicoReference")
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Cita> citas;
}
