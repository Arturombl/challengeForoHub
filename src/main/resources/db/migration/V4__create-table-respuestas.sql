create table respuestas(

    id bigint not null auto_increment,
    mensaje varchar(100) not null unique,
    solucion varchar(100) not null unique,
    fecha datetime not null,
    topico_id  bigint not null,
    usuario_id  bigint not null,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id),
    constraint fk_respuestas_usuario_id foreign key (usuario_id) references usuarios(id)
)