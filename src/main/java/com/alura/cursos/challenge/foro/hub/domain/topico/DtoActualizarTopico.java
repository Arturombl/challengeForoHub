package com.alura.cursos.challenge.foro.hub.domain.topico;

import java.time.LocalDateTime;

public record DtoActualizarTopico(
        String titulo,
        String mensaje,
        String curso,
        String usuario,
        LocalDateTime fecha
) {
}
