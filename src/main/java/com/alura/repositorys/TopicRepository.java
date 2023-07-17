package com.alura.repositorys;

import com.alura.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topico,Long> {
}
