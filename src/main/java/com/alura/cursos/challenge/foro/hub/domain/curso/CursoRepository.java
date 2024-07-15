package com.alura.cursos.challenge.foro.hub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
    Optional buscarPorNombre(String nombre);
}
