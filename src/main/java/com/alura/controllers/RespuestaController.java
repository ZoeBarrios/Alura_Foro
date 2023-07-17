package com.alura.controllers;

import com.alura.DTOS.RespuestaDTOS.DatosCrearRespuesta;
import com.alura.DTOS.RespuestaDTOS.DatosDevolverRespuesta;
import com.alura.DTOS.RespuestaDTOS.DatosActualizarRespuesta;
import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import com.alura.services.RespuestaService;
import com.alura.services.TopicService;
import com.alura.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    RespuestaService respuestaService;
    @Autowired
    TopicService topicService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Devuelve todas las respuestas")
    @GetMapping
    public ResponseEntity<Stream<DatosDevolverRespuesta>> obtenerRespuestas(){
        List<Respuesta> respuestas=respuestaService.getAllResponses();
        return ResponseEntity.ok().body(respuestas.stream().map(respuesta->new DatosDevolverRespuesta(respuesta)));
    }
    @Operation(summary = "Permite crear 1 respuesta")
    @PostMapping
    public ResponseEntity<DatosDevolverRespuesta> crearRespuesta(@RequestBody DatosCrearRespuesta datosCrearRespuesta, UriComponentsBuilder uriComponentsBuilder){
        Topico tp=topicService.getTopico(datosCrearRespuesta.topico());
        Usuario us=usuarioService.getUsuarioById(datosCrearRespuesta.usuario());
        Respuesta rs=respuestaService.createResponse(new Respuesta(datosCrearRespuesta.mensaje(),tp,us));
        URI url=uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(rs.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDevolverRespuesta(rs));
    }

    @Operation(summary = "Devuelve 1 respuesta")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDevolverRespuesta> obtenerRespuesta(@PathVariable Long id){
        Respuesta respuesta=respuestaService.getResponse(id);
        return ResponseEntity.ok().body(new DatosDevolverRespuesta(respuesta));
    }

    @Operation(summary = "Permite eliminar 1 respuesta")
    @DeleteMapping
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.deleteResponse(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Permite actualizar 1 respuesta")
    @PutMapping
    @Transactional
    public ResponseEntity<DatosDevolverRespuesta> actualizarRespuesta(@RequestBody DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta res=respuestaService.updateResponse(datosActualizarRespuesta);
        return ResponseEntity.ok().body(new DatosDevolverRespuesta(res));
    }

}
