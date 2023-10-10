package citas_medicas.controllers;

import citas_medicas.models.Medico;
import citas_medicas.models.Paciente;
import citas_medicas.services.MedicoService;
import citas_medicas.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico createdMedico = medicoService.createMedico(medico);
        return ResponseEntity.ok(createdMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico updateMedico) {
        Medico medico = medicoService.updateMedico(updateMedico, id);

        if (medico != null) {
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedico(@PathVariable Long id) {
        boolean isDeleted = medicoService.deleteMedico(id);

        if (isDeleted) {
            return ResponseEntity.ok("Medico con ID: " + id + " fue eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico con ID: " + id + " no existe");
        }
    }

    @GetMapping("/{id}/pacientes")
    public ResponseEntity<Set<Paciente>> getPacientesByMedicoId(@PathVariable Long id) {
        Set<Paciente> pacientes = medicoService.getPacientesByMedicoId(id);
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping("/{medicoId}/asignar-paciente/{pacienteId}")
    public ResponseEntity<String> asignarPaciente(@PathVariable Long medicoId, @PathVariable Long pacienteId) {
        medicoService.asignarPaciente(medicoId, pacienteId);
        return ResponseEntity.ok("Paciente asignado al médico correctamente.");
    }

    @PostMapping("/{medicoId}/desvincular-paciente/{pacienteId}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long medicoId, @PathVariable Long pacienteId) {
        medicoService.eliminarPaciente(medicoId, pacienteId);
        return ResponseEntity.ok("Paciente eliminado del médico correctamente.");
    }

}
