package com.alura.cursos.challenge.foro.hub.domain.topico;

import java.time.LocalDateTime;

public record DtoListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean estado,
        String usuario,
        String curso
) {
    public static DtoListadoTopicos fromTopico(Topico topico) {
        return new DtoListadoTopicos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
