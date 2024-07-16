package com.alura.cursos.challenge.foro.hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoActualizarTopico(
        String titulo,
        String mensaje,
        String curso,
        String usuario,
        LocalDateTime fecha
) {
}
