package com.alura.controllers;

import com.alura.DTOS.UsuariosDTOS.DatosActualizarUsuario;
import com.alura.DTOS.UsuariosDTOS.DatosDevolverUsuario;
import com.alura.DTOS.UsuariosDTOS.DatosRegistroUsuario;
import com.alura.modelo.Usuario;
import com.alura.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Operation(summary = "Permite registrar 1 usuario")
    @PostMapping
    public ResponseEntity<DatosDevolverUsuario> registrarUsuario(@RequestBody DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        String contraseñaHasheada = passwordEncoder.encode(datosRegistroUsuario.contrasena());
        Usuario usuario=usuarioService.createUser(new Usuario(datosRegistroUsuario),contraseñaHasheada);
        URI url= uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDevolverUsuario(usuario));
    }

    @Operation(summary = "Devuelve todos los usuarios")
    @GetMapping
    public ResponseEntity<Stream<DatosDevolverUsuario>> devolverUsuarios(){
        List<Usuario> usuarios=usuarioService.getUsers();
        return ResponseEntity.ok().body(usuarios.stream().map(usuario->new DatosDevolverUsuario(usuario)));
    }

    @Operation(summary = "Permite eliminar 1 usuario")
    @Transactional
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Permite actualizar 1 usuario")
    @SecurityRequirement(name = "bearerAuth")
    @Transactional
    @PutMapping
    public ResponseEntity actualizarUsuario(@RequestBody DatosActualizarUsuario datosActualizarUsuario){
        Usuario usuario=usuarioService.updateUser(datosActualizarUsuario);
        return ResponseEntity.ok().body(new DatosDevolverUsuario(usuario));
    }

    @Operation(summary = "Devuelve 1 usuario")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDevolverUsuario> devolverUsuario(@PathVariable Long id){
        Usuario usuario=usuarioService.getUsuarioById(id);

        return ResponseEntity.ok().body(new DatosDevolverUsuario(usuario));
    }
}
