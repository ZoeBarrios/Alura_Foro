package com.alura.controllers;
import com.alura.DTOS.TopicosDTOS.DatosActualizarTopico;
import com.alura.DTOS.TopicosDTOS.DatosDevolverTopico;
import com.alura.DTOS.TopicosDTOS.DatosRegistrarTopico;
import com.alura.modelo.Curso;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.services.CursoService;
import com.alura.services.TopicService;
import com.alura.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    TopicService topicService;
    @Autowired
    CursoService cursoService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Permite crear 1 topico")
    @PostMapping
    public ResponseEntity<DatosRegistrarTopico> registrarTopico(@RequestBody @Valid  DatosRegistrarTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Curso cursoEncontrado=cursoService.getCursoById(datos.curso());
        Usuario usuarioEncontrado=usuarioService.getUsuarioById(datos.usuario());
        Topico topico=topicService.createTopico(new Topico(datos.titulo(), datos.mensaje(), cursoEncontrado,usuarioEncontrado));
        URI url=uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @Operation(summary = "Devuelve todos los topicos")
    @GetMapping
    public ResponseEntity<Stream<DatosDevolverTopico>> getTopicos(){
        List<Topico> topicos=topicService.getTopicos();
        return ResponseEntity.ok(topicos.stream().map((topico -> new DatosDevolverTopico(topico))));

    }

    @Operation(summary = "Permite obtener 1 topico")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDevolverTopico> obtenerTopico(@PathVariable Long id){
        Topico topico=topicService.getTopico(id);

        return ResponseEntity.ok().body(new DatosDevolverTopico(topico));
    }

    @Operation(summary = "Permite actualizar 1 topico")
    @Transactional
    @PutMapping
    public ResponseEntity<DatosDevolverTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico=topicService.updateTopico(datosActualizarTopico);
        return ResponseEntity.ok().body(new DatosDevolverTopico(topico));

    }

    @Operation(summary = "Permite eliminar 1 topico")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity elminarTopico(@PathVariable Long id){
        Topico topico=topicService.getTopico(id);
        topicService.deleteTopico(topico);
        return ResponseEntity.noContent().build();
    }



}
