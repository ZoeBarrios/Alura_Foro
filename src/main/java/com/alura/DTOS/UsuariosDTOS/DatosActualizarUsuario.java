package com.alura.DTOS.UsuariosDTOS;

import io.swagger.v3.oas.annotations.Hidden;


public record DatosActualizarUsuario(Long id,String nombre,String email) {
}
