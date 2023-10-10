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
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        return paciente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente createdPaciente = pacienteService.createPaciente(paciente);
        return ResponseEntity.ok(createdPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente updatePaciente) {
        Paciente paciente = pacienteService.updatePaciente(updatePaciente, id);

        if(paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) {
        boolean isDeleted = pacienteService.deletePaciente(id);

        if(isDeleted) {
            return ResponseEntity.ok("Paciente con ID: " + id + " fue eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente con ID: " + id + " no existe");
        }
    }

    @GetMapping("/{id}/medicos")
    public ResponseEntity<Set<Medico>> getMedicosByPacienteId(@PathVariable Long id) {
        Set<Medico> medicos = pacienteService.getMedicosByPacienteId(id);
        return ResponseEntity.ok(medicos);
    }

    @PostMapping("/{pacienteId}/asignar-medico/{medicoId}")
    public ResponseEntity<String> asignarMedico(@PathVariable Long pacienteId, @PathVariable Long medicoId) {
        pacienteService.asignarMedico(pacienteId, medicoId);
        return ResponseEntity.ok("Médico asignado al paciente correctamente.");
    }

    @PostMapping("/{pacienteId}/desvincular-medico/{medicoId}")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long pacienteId, @PathVariable Long medicoId) {
        pacienteService.eliminarMedico(pacienteId, medicoId);
        return ResponseEntity.ok("Médico desvinculado del paciente correctamente.");
    }
}
