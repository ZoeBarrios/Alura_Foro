package com.alura.repositorys;

import com.alura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
}
