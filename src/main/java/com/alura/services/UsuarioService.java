package com.alura.services;

import com.alura.DTOS.UsuariosDTOS.DatosActualizarUsuario;
import com.alura.excepcions.ExcepcionesPersonalizadas.UserNotFoundException;
import com.alura.modelo.Usuario;
import com.alura.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario, String contraseñaHasheada){
        usuario.setContrasena(contraseñaHasheada);
        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Long id){
        Usuario usuario=usuarioRepository.getById(id);
        usuarioRepository.delete(usuario);
    }
    public Usuario getUsuarioById(long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public List<Usuario> getUsers(){
        return usuarioRepository.findAll();
    }

    public Usuario updateUser(DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario=getUsuarioById(datosActualizarUsuario.id());
        usuario.actualizar(datosActualizarUsuario);
        return usuario;
    }
}
