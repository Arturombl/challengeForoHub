package com.alura.cursos.challenge.foro.hub.infra.security;



import com.alura.cursos.challenge.foro.hub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    @Value("${hub.security.token.secret}")
    private String secret;

    public String generearToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(usuario.getNombre())
                    .withClaim("id: ", usuario.getId())
                    .withExpiresAt(generatFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        if (token == null){
            System.out.println("token es nulo");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);//Validando la Firma
            verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer("Foro Hub")//verifica que el emisor del token sea correcto
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("No se pudo obtener el subject del token");
        }
        return verifier.getSubject();
    }

    private Instant generatFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-07:00"));
    }
}
