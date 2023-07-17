package com.alura.DTOS.TopicosDTOS;

import com.alura.modelo.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistrarTopico(Long id, @NotBlank String titulo, @NotBlank  String mensaje, @NotNull long usuario, @NotNull long curso) {


}
