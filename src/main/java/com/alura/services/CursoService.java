package com.alura.services;

import com.alura.DTOS.CursosDTOS.DatosActualizarCurso;
import com.alura.excepcions.ExcepcionesPersonalizadas.UserNotFoundException;
import com.alura.modelo.Curso;
import com.alura.repositorys.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;
    public Curso getCursoById(long id){
        return cursoRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Curso no encontrado con ID: " + id));
    }

    public void deleteCurso(Curso curso){
        cursoRepository.delete(curso);
    }

    public Curso updateCurso(DatosActualizarCurso datosCrearCurso){
        Curso curso=cursoRepository.getById(datosCrearCurso.id());
        curso.actualizarCurso(datosCrearCurso);
        cursoRepository.save(curso);
        return curso;
    }
    public Curso createCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Page<Curso> getCursos(Pageable pageable){
        return cursoRepository.findAll(pageable);
    }
}
