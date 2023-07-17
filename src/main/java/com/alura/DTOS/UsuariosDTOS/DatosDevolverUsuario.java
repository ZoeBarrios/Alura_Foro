package com.alura.DTOS.UsuariosDTOS;


import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.Hidden;


public record DatosDevolverUsuario(Long id, String nombre, String email) {
    public DatosDevolverUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(),usuario.getEmail());
    }
}
