package com.alura.cursos.challenge.foro.hub.domain.topico.validaciones;

import com.alura.cursos.challenge.foro.hub.domain.topico.DtoRegistroTopico;
import com.alura.cursos.challenge.foro.hub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TituloDuplicado implements ValidadorDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DtoRegistroTopico dtoRegistroTopico){
        if (dtoRegistroTopico.titulo() == null ){
            return;
        }
        var tituloBuscado = topicoRepository.buscarPorTitulo(dtoRegistroTopico.titulo());

        if(tituloBuscado.isPresent()){
            throw new ValidationException("No permite crear Topicos con el mismo Titulo en el sistemas");
        }
    }

}
