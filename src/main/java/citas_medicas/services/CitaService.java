package citas_medicas.services;

import citas_medicas.models.Cita;
import citas_medicas.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    CitaRepository citaRepository;

    public List<Cita> getAllCitasService() { return citaRepository.findAll(); }
    public Cita getOneCitaService(Long id) { return citaRepository.findById(id).orElse(null); }
    public Cita createCitaService(Cita newCita) { return citaRepository.save(newCita); }
    public Cita updateCitaService(Cita updateCita, Long id) {
        Optional<Cita> optionalCita = citaRepository.findById(id);

        if(optionalCita.isPresent()) {
            Cita existingCita = optionalCita.get();

            existingCita.setFecha_hora(updateCita.getFecha_hora());
            existingCita.setMotivo_cita(updateCita.getMotivo_cita());

            return citaRepository.save(existingCita);
        } else {
            return null;
        }
    }
    public boolean deleteCitaService(Long id) {
        Optional<Cita> optionalCita = citaRepository.findById(id);

        if(optionalCita.isPresent()) {
            citaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
