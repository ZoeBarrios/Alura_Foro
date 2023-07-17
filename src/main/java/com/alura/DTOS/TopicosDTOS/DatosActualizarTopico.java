package com.alura.DTOS.TopicosDTOS;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,  String mensaje,  String titulo) {
}
