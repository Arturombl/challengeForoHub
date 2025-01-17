package com.alura.cursos.challenge.foro.hub.domain.topico;

import com.alura.cursos.challenge.foro.hub.domain.curso.Curso;
import com.alura.cursos.challenge.foro.hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String mensaje;
    private LocalDateTime fecha;
    private Boolean estado;
//    private String autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(String titulo, String mensaje, LocalDateTime fecha,Boolean estado, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarDatos(DtoActualizarTopico dtoActualizarTopico, LocalDateTime fecha) {
        if (dtoActualizarTopico.titulo() != null){
            this.titulo = dtoActualizarTopico.titulo();
        }
        if (dtoActualizarTopico.mensaje() != null){
            this.mensaje = dtoActualizarTopico.mensaje();
        }
        if (dtoActualizarTopico.fecha() != null){
            this.fecha = fecha;
        }
    }
}
