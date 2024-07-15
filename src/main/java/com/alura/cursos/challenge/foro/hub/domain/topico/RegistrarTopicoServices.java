package com.alura.cursos.challenge.foro.hub.domain.topico;

import com.alura.cursos.challenge.foro.hub.domain.curso.Curso;
import com.alura.cursos.challenge.foro.hub.domain.curso.CursoRepository;
import com.alura.cursos.challenge.foro.hub.domain.topico.validaciones.ValidadorDeTopicos;
import com.alura.cursos.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.cursos.challenge.foro.hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegistrarTopicoServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    List<ValidadorDeTopicos> validador;

    public DetallesDeTopico registrar(DtoRegistroTopico dtoRegistroTopico){
        System.out.println(dtoRegistroTopico);
        if (!usuarioRepository.buscarPorNombre(dtoRegistroTopico.usuario()).isPresent()){
            throw new ValidacionDeIntegradad("Este id para usuario no fue encontrado");
        }
        if (!cursoRepository.buscarPorNombre(dtoRegistroTopico.curso()).isPresent()){
            throw new ValidacionDeIntegradad("Este id para curso no fue encontrado");
        }

        validador.forEach(v-> v.validar(dtoRegistroTopico));

        var curso = cursoRepository.buscarPorNombre(dtoRegistroTopico.curso()).get();
        var usuario = usuarioRepository.buscarPorNombre(dtoRegistroTopico.usuario()).get();
        var fechaActual = LocalDateTime.now();
        var topico = new Topico(dtoRegistroTopico.titulo(), dtoRegistroTopico.mensaje(), fechaActual, (Usuario) usuario, (Curso) curso);

        topicoRepository.save(topico);
        var formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        String fechaFormateada = fechaActual.format(formatter);

        return DetallesDeTopico.fromTopico(topico, fechaFormateada);
    }
}
