package citas_medicas.services;

import citas_medicas.models.Medico;
import citas_medicas.models.Paciente;
import citas_medicas.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacienteService() { return pacienteRepository.findAll(); }

    public Paciente getOnePacienteService(Long id) { return pacienteRepository.findById(id).orElse(null);}

    public Paciente createPacienteService(Paciente paciente) { return pacienteRepository.save(paciente); }

    public Paciente updatePacienteService(Paciente paciente, Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if(optionalPaciente.isPresent()){
            Paciente existingPaciente = optionalPaciente.get();

            existingPaciente.setNss(existingPaciente.getNss());
            existingPaciente.setNum_tarjeta(existingPaciente.getNum_tarjeta());
            existingPaciente.setTelefono(existingPaciente.getTelefono());
            existingPaciente.setDireccion(existingPaciente.getDireccion());

            return pacienteRepository.save(existingPaciente);
        } else {
            return null;
        }
    }

    public boolean deletePacienteService(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if(optionalPaciente.isPresent()) {
            pacienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
