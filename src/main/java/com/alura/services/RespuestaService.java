package com.alura.services;

import com.alura.DTOS.RespuestaDTOS.DatosActualizarRespuesta;
import com.alura.modelo.Respuesta;
import com.alura.repositorys.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    RespuestaRepository respuestaRepository;
    public List<Respuesta> getAllResponses(){
        return respuestaRepository.findAll();
    }

    public Respuesta updateResponse(DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta=getResponse(datosActualizarRespuesta.id());
        respuesta.actualizar(datosActualizarRespuesta);
        return respuesta;
    }
    public Respuesta getResponse(Long id){
        return respuestaRepository.getById(id);
    }

    public void deleteResponse(Long id){
        respuestaRepository.deleteById(id);
    }
    public Respuesta createResponse(Respuesta respuesta){
        return respuestaRepository.save(respuesta);
    }
}
