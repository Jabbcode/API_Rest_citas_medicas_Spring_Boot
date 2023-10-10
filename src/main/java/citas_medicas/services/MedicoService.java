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
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico createMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico updateMedico(Medico medico, Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            Medico existingMedico = optionalMedico.get();

            existingMedico.setNombre(medico.getNombre());
            existingMedico.setApellido(medico.getApellido());
            existingMedico.setNum_colegiado(medico.getNum_colegiado());

            return medicoRepository.save(existingMedico);
        } else {
            return null;
        }
    }

    public boolean deleteMedico(Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            medicoRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    public Set<Paciente> getPacientesByMedicoId(Long medicoId) {
        Optional<Medico> medicoOptional = medicoRepository.findById(medicoId);
        return medicoOptional.map(Medico::getPacientes).orElse(new HashSet<>());
    }

    public void asignarPaciente(Long medicoId, Long pacienteId) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);

        if (medico != null && paciente != null) {
            medico.asignarPaciente(paciente);
            medicoRepository.save(medico);
        }
    }

    public void eliminarPaciente(Long medicoId, Long pacienteId) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);

        if (medico != null && paciente != null) {
            medico.eliminarPaciente(paciente);
            medicoRepository.save(medico);
        }
    }
}
