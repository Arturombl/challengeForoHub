package com.alura.cursos.challenge.foro.hub.controller;


import com.alura.cursos.challenge.foro.hub.domain.usuario.DatosAutenticacion;
import com.alura.cursos.challenge.foro.hub.domain.usuario.Usuario;
import com.alura.cursos.challenge.foro.hub.infra.security.DatosJWTToken;
import com.alura.cursos.challenge.foro.hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.nombre(), datosAutenticacion.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        if (usuarioAutenticado.isAuthenticated()) {
            System.out.println(usuarioAutenticado);
            var JWTToken = tokenService.generearToken((Usuario) usuarioAutenticado.getPrincipal());
//        System.out.println(JWTToken);
            return ResponseEntity.ok(new DatosJWTToken(JWTToken));
        } else {
            System.out.println("fallo la autentificacion");
        }
        return null;
    }
}
