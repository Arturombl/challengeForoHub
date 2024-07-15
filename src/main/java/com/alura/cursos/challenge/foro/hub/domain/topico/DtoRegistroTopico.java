package com.alura.cursos.challenge.foro.hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroTopico(

        @NotBlank
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String curso,
        @NotNull
        String usuario
) {
}
