create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fecha datetime not null,
    curso_id  bigint not null,
    usuario_id  bigint not null,

    primary key(id),

    constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id),
    constraint fk_topicos_usuario_id foreign key (usuario_id) references usuarios(id)
)