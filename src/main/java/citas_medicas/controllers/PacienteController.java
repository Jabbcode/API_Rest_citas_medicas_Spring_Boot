package citas_medicas.controllers;

import citas_medicas.exceptions.CitaNotFoundException;
import citas_medicas.models.Paciente;
import citas_medicas.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllMedicoController() {
        List<Paciente> pacientes = pacienteService.getAllPacienteService();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente newPaciente = pacienteService.createPacienteService(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPaciente);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
