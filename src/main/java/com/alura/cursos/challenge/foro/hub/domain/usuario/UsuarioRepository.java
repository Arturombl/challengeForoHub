package com.alura.cursos.challenge.foro.hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByNombre(String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Optional buscarPorNombre(String nombre);
}
