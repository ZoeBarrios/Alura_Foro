package com.alura.controllers;

import com.alura.DTOS.UsuariosDTOS.DatosAutenticacionUsuario;
import com.alura.configuration.infra.JWTInfo;
import com.alura.configuration.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        try {
            // Crear objeto Authentication con las credenciales proporcionadas
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    datosAutenticacionUsuario.nombre(),
                    datosAutenticacionUsuario.contrasena()
            );


            // Autenticar el usuario
            Authentication authenticated = authenticationManager.authenticate(authentication);


            // Generar el token JWT
            String token = jwtService.generarToken(authenticated);

            // Retornar el token en la respuesta
            return ResponseEntity.ok(new JWTInfo(token));
        } catch (AuthenticationException e) {
            // Manejar el error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación");
        }
    }

}