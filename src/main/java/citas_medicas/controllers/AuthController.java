package citas_medicas.controllers;

import citas_medicas.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> LoginController(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String clave = credentials.get("clave");

        String mensaje = authService.LoginService(username, clave);

        return ResponseEntity.ok(mensaje);
    }
}