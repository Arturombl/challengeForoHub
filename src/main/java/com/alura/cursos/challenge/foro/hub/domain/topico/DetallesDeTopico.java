package com.alura.cursos.challenge.foro.hub.domain.topico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DetallesDeTopico(
        String titulo,
        String mensaje,
        Boolean estado,
        String usuario,
        String curso,
        LocalDateTime fecha
) {
    public static DetallesDeTopico fromTopico(Topico topico, String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        LocalDateTime fechaFormateada = LocalDateTime.parse(fecha, formatter);
        return new DetallesDeTopico(topico.getTitulo(), topico.getMensaje(),topico.getEstado(), topico.getUsuario().getNombre(),topico.getCurso().getNombre(), fechaFormateada);
    }
}
