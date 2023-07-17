package com.alura.DTOS.RespuestaDTOS;

import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;
import io.swagger.v3.oas.annotations.Hidden;


    public record DatosDevolverRespuesta(Long id, String mensaje, String topico, String usuario) {
    public DatosDevolverRespuesta(Respuesta rs) {
        this(rs.getId(),rs.getMensaje(),rs.getTopico().getTitulo(),rs.getAutor().getNombre());
    }
}
