package com.alura.cursos.challenge.foro.hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.titulo = :titulo")
    Optional buscarPorTitulo(String titulo);

    @Query("SELECT t FROM Topico t WHERE t.mensaje = :mensaje")
    Optional buscarPorMensaje(String mensaje);

}
