package com.alura.cursos.challenge.foro.hub.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public record DtoRegistroTopico(

        @NotBlank
        String titulo,
        @NotNull
        String mensaje,
        @NotBlank
        @Future
        LocalDateTime fecha,
        Boolean status,
        @NotNull
        Long idCurso,
        @NotNull
        Long idUsuario,
        @NotBlank
        String autor


) {
}
