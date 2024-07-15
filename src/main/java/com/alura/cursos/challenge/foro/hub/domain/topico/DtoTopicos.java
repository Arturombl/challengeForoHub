package com.alura.cursos.challenge.foro.hub.domain.topico;

import com.alura.cursos.challenge.foro.hub.domain.curso.Curso;
import com.alura.cursos.challenge.foro.hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoTopicos(

        String titulo,
        String mensaje,
        LocalDateTime fecha,
//        Boolean status,
//        String autor,
        Usuario usuario,
        Curso curso
) {
}
