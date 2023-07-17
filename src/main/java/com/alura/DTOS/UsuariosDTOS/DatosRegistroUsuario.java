package com.alura.DTOS.UsuariosDTOS;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record DatosRegistroUsuario(Long id, @NotBlank String nombre, @NotBlank @Email String email,@NotBlank @Pattern(regexp="^[a-zA-Z0-9]{5}",message="Debe tener una longitud minima de 3 caracteres")   String contrasena) {
}
