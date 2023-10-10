package citas_medicas.repositories;

import citas_medicas.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Agrega métodos específicos para Medico si los necesitas
}
