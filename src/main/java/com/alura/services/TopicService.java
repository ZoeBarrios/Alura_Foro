package com.alura.services;

import com.alura.DTOS.TopicosDTOS.DatosActualizarTopico;
import com.alura.excepcions.ExcepcionesPersonalizadas.UserNotFoundException;
import com.alura.modelo.Topico;
import com.alura.repositorys.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;
    public Topico createTopico(Topico datos){

        return topicRepository.save(datos);

    }

    public Topico getTopico(Long id){
        return topicRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Topico no encontrado con ID: " + id));
    }

    public void deleteTopico(Topico topico){
        topicRepository.delete(topico);
    }

    public Topico updateTopico(DatosActualizarTopico datosActualizarTopico){
        Topico topico=getTopico(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return topico;
    }

    public List<Topico> getTopicos(){
        return topicRepository.findAll();
    }
}
