package com.alura.cursos.challenge.foro.hub.controller;

import com.alura.cursos.challenge.foro.hub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoServices topicoServices;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DtoRegistroTopico dtoRegistroTopico){
        var topico = topicoServices.registrar(dtoRegistroTopico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DtoListadoTopicos>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion){
        Sort sort = Sort.by(Sort.Direction.DESC, "fecha"); // Ordena por fecha en orden descendente
        Pageable pageable = PageRequest.of(paginacion.getPageNumber(), paginacion.getPageSize(), sort);

        return ResponseEntity.ok(topicoRepository.findByEstadoTrue(pageable)
                .map(DtoListadoTopicos::fromTopico));
    }

    @GetMapping("/{id}")
    public ResponseEntity retornarDatosTopicos(@PathVariable @Valid Long id){
        topicoServices.validarTopico(id);
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DetallesDeTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFecha()
        );
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetallesDeTopico> actualizarTopico(@PathVariable @Valid Long id, @RequestBody @Valid DtoActualizarTopico dtoRegistroTopico){
        topicoServices.validarTopico(id);
        Topico topico = topicoRepository.getReferenceById(id);
        var fechaActual = LocalDateTime.now();
        topico.actualizarDatos(dtoRegistroTopico, fechaActual);
        return ResponseEntity.ok(new DetallesDeTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFecha()
                )
        );
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoServices.validarTopico(id);
        topicoRepository.deleteById(topicoRepository.getReferenceById(id).getId());
        return ResponseEntity.noContent().build();
    }
}
