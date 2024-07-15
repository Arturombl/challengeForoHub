package com.alura.cursos.challenge.foro.hub.controller;

import com.alura.cursos.challenge.foro.hub.domain.topico.DtoRegistroTopico;
import com.alura.cursos.challenge.foro.hub.domain.topico.RegistrarTopicoServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private RegistrarTopicoServices registrarTopicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DtoRegistroTopico dtoRegistroTopico){
        var topico = registrarTopicoServices.registrar(dtoRegistroTopico);
        return ResponseEntity.ok(topico);
    }
}
