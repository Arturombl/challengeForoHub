package com.alura.cursos.challenge.foro.hub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/topicos")
public class TopicoController {

    @GetMapping
    public String Hello(){
        return "Hola funcionando";
    }
}
