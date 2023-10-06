package citas_medicas.exceptions;

public class CitaNotFoundException extends RuntimeException {

    public CitaNotFoundException(Long citaId) {
        super("No se encontró la cita con ID: " + citaId);
    }
}
