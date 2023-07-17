package com.alura.DTOS.CursosDTOS;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;

public record DatosCrearCurso(Long id, @NotBlank String nombre, @NotBlank String categoria) {
}
