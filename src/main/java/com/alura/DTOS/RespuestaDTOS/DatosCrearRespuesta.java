package com.alura.DTOS.RespuestaDTOS;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearRespuesta(@NotBlank String mensaje, @NotNull Long topico,@NotNull Long usuario) {
}
