package com.alura.DTOS.TopicosDTOS;

import com.alura.modelo.Curso;
import com.alura.modelo.StatusTopico;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.Hidden;

import java.time.LocalDateTime;

public record DatosDevolverTopico(Long id,String titulo, String mensaje, LocalDateTime fechaCreacion, StatusTopico status,
                                  String usuario, String curso) {
public DatosDevolverTopico(Topico topico){
    this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getfechaCreacion(),topico
            .getStatus(),topico.getAutor().getNombre(),topico.getCurso().getNombre());

}
}
