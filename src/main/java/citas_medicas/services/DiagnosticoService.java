package citas_medicas.services;

import citas_medicas.exceptions.CitaNotFoundException;
import citas_medicas.models.Cita;
import citas_medicas.models.Diagnostico;
import citas_medicas.repositories.CitaRepository;
import citas_medicas.repositories.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private CitaRepository citaRepository;

    public List<Diagnostico> getAllDiagnosticosService() { return diagnosticoRepository.findAll();}
    public Diagnostico getOneDiagnosticoService(Long id) {return diagnosticoRepository.findById(id).orElse(null);}

    public Diagnostico createDiagnosticoService(Diagnostico diagnostico) {
        Long citaId = diagnostico.getCita().getId();
        Optional<Cita> optionalCita = citaRepository.findById(citaId);

        if (optionalCita.isEmpty()) {
            throw new CitaNotFoundException(citaId);
        } else {
            Cita cita = optionalCita.get();
            diagnostico.setCita(cita);
            return diagnosticoRepository.save(diagnostico);
        }
    }

    public Diagnostico updateDiagnosticoService(Diagnostico updateDiagnostico, Long id) {
        Optional<Diagnostico> optionalDiagnostico =  diagnosticoRepository.findById(id);

        if(optionalDiagnostico.isPresent()) {
            Diagnostico existingDiagnostico = optionalDiagnostico.get();

            existingDiagnostico.setValoracion_especialista(updateDiagnostico.getValoracion_especialista());
            existingDiagnostico.setEnfermedad(updateDiagnostico.getEnfermedad());

            return diagnosticoRepository.save(existingDiagnostico);
        } else {
            return null;
        }
    }

    public boolean deleteDiagnosticoService(Long id) {
        Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(id);

        if (optionalDiagnostico.isPresent()) {
            diagnosticoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
