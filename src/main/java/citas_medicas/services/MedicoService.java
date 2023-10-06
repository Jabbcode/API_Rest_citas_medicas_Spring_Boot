package citas_medicas.services;

import citas_medicas.models.Medico;
import citas_medicas.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAllMedicoService() { return medicoRepository.findAll(); }

    public Medico getOneMedicoService(Long id) { return medicoRepository.findById(id).orElse(null);}

    public Medico createMedicoService(Medico medico) { return medicoRepository.save(medico); }

    public Medico updateMedicoService(Medico medico, Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if(optionalMedico.isPresent()){
            Medico existingMedico = optionalMedico.get();

            existingMedico.setNum_colegiado(existingMedico.getNum_colegiado());
            return medicoRepository.save(existingMedico);
        } else {
            return null;
        }
    }

    public boolean deleteMedicoService(Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if(optionalMedico.isPresent()) {
            medicoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
