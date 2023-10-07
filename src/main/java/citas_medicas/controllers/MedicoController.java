package citas_medicas.controllers;

import citas_medicas.exceptions.CitaNotFoundException;
import citas_medicas.models.Medico;
import citas_medicas.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicoController() {
        List<Medico> medicos = medicoService.getAllMedicoService();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        try {
            Medico newMedico = medicoService.createMedicoService(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMedico);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
