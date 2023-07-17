package com.alura.controllers;

import com.alura.DTOS.CursosDTOS.DatosCrearCurso;
import com.alura.DTOS.CursosDTOS.DatosDevolverCurso;
import com.alura.DTOS.CursosDTOS.DatosActualizarCurso;
import com.alura.modelo.Curso;
import com.alura.services.CursoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Operation(summary = "Permite crear 1 curso")
    @PostMapping
    public ResponseEntity<DatosDevolverCurso> crearCurso(@RequestBody @Valid DatosCrearCurso datosCrearCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso=cursoService.createCurso(new Curso((datosCrearCurso)));
        URI url=uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDevolverCurso(curso));

    }


    @Operation(summary = "Devuelve todos los cursos")
    @GetMapping
    public ResponseEntity<Stream<DatosDevolverCurso>> devolverCursos(Pageable pageable ){
        Page<Curso> cursos=  cursoService.getCursos(pageable);
        return ResponseEntity.ok(cursos.stream().map(curso -> new DatosDevolverCurso(curso)));
    }

    @Operation(summary = "Devuelve 1 curso")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDevolverCurso> obtenerCurso(@PathVariable Long id){
        Curso curso=cursoService.getCursoById(id);
        return ResponseEntity.ok().body(new DatosDevolverCurso(curso));
    }

    @Operation(summary = "Elimina 1 curso")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        Curso curso=cursoService.getCursoById(id);
        cursoService.deleteCurso(curso);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualiza 1 curso")
    @Transactional
    @PutMapping
    public ResponseEntity<DatosDevolverCurso> actualizarTopico(@RequestBody DatosActualizarCurso datosActualizarCurso){
        Curso curso=cursoService.updateCurso(datosActualizarCurso);
        return ResponseEntity.ok().body(new DatosDevolverCurso(curso));

    }
}
