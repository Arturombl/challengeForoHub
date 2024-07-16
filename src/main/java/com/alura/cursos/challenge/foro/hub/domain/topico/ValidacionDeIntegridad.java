package com.alura.cursos.challenge.foro.hub.domain.topico;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
