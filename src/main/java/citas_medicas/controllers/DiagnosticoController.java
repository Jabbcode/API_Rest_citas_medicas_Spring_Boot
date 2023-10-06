package citas_medicas.controllers;

import citas_medicas.exceptions.CitaNotFoundException;
import citas_medicas.models.Diagnostico;
import citas_medicas.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public ResponseEntity<List<Diagnostico>> getAllDiagnosticosController() {
        List<Diagnostico> diagnosticos = diagnosticoService.getAllDiagnosticosService();

        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> getOneDiagnosticoController(@PathVariable Long id) {
        Diagnostico diagnostico = diagnosticoService.getOneDiagnosticoService(id);

        if (diagnostico != null) {
            return ResponseEntity.ok(diagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Diagnostico> createDiagnosticoController(@RequestBody Diagnostico diagnostico) {
        try {
            Diagnostico newDiagnostico = diagnosticoService.createDiagnosticoService(diagnostico);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDiagnostico);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> updateDiagnosticoController(@PathVariable Long id, @RequestBody Diagnostico updateDiagnostico) {
        Diagnostico diagnostico = diagnosticoService.updateDiagnosticoService(updateDiagnostico, id);

        if(diagnostico != null) {
            return ResponseEntity.ok(diagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiagnosticoController(@PathVariable Long id) {
        boolean isDeleted = diagnosticoService.deleteDiagnosticoService(id);

        if(isDeleted) {
            return ResponseEntity.ok("Diagnostico con ID: " + id + " fue eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diagnostico con ID: " + id + " no existe");
        }
    }
}
