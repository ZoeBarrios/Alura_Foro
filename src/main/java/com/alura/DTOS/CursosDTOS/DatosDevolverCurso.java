package com.alura.DTOS.CursosDTOS;

import com.alura.modelo.Curso;
import io.swagger.v3.oas.annotations.Hidden;


public record DatosDevolverCurso(Long id, String nombre, String categoria) {
    public DatosDevolverCurso(Curso curso){
        this(curso.getId(),curso.getNombre(),curso.getCategoria());
    }
}
