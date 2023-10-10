package citas_medicas.services;

import citas_medicas.models.Medico;
import citas_medicas.models.Paciente;
import citas_medicas.repositories.MedicoRepository;
import citas_medicas.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente createPaciente(Paciente paciente) {
        // Puedes realizar validaciones o lógica adicional antes de guardar al paciente
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(Paciente paciente, Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente existingPaciente = optionalPaciente.get();

            existingPaciente.setNombre(paciente.getNombre());
            existingPaciente.setApellido(paciente.getApellido());
            existingPaciente.setNss(paciente.getNss());
            existingPaciente.setNum_tarjeta(paciente.getNum_tarjeta());
            existingPaciente.setTelefono(paciente.getTelefono());
            existingPaciente.setDireccion(paciente.getDireccion());

            return pacienteRepository.save(existingPaciente);
        } else {
            return null;
        }
    }

    public boolean deletePaciente(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if(optionalPaciente.isPresent()) {
            pacienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Set<Medico> getMedicosByPacienteId(Long pacienteId) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        return pacienteOptional.map(Paciente::getMedicos).orElse(new HashSet<>());
    }

    public void asignarMedico(Long pacienteId, Long medicoId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        Medico medico = medicoRepository.findById(medicoId).orElse(null);

        if (paciente != null && medico != null) {
            paciente.asignarMedico(medico);
            pacienteRepository.save(paciente);
        }
    }

    public void eliminarMedico(Long pacienteId, Long medicoId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        Medico medico = medicoRepository.findById(medicoId).orElse(null);

        if (paciente != null && medico != null) {
            paciente.eliminarMedico(medico);
            pacienteRepository.save(paciente);
        }
    }
}
