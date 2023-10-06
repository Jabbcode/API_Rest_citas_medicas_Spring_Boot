package citas_medicas.controllers;

import citas_medicas.models.Cita;
import citas_medicas.repositories.CitaRepository;
import citas_medicas.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitaController() {
        List<Cita> citas = citaService.getAllCitasService();

        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getOneCitaController(@PathVariable Long id) {
        Cita cita = citaService.getOneCitaService(id);

        if (cita != null) {
            return ResponseEntity.ok(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cita> createCitaController(@RequestBody Cita cita) {
        Cita newCita = citaService.createCitaService(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCitaController(@PathVariable Long id, @RequestBody Cita updateCita) {
        Cita cita = citaService.updateCitaService(updateCita, id);

        if(cita != null) {
            return ResponseEntity.ok(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCitaController(@PathVariable Long id) {
        boolean isDeleted = citaService.deleteCitaService(id);

        if(isDeleted) {
            return ResponseEntity.ok("Cita con ID: " + id + " fue eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita con ID: " + id + " no existe");
        }
    }
}
