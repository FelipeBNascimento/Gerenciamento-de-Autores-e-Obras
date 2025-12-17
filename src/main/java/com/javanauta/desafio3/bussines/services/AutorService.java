package com.javanauta.desafio3.bussines.services;


import com.javanauta.desafio3.bussines.converter.Mapper;
import com.javanauta.desafio3.bussines.converter.MapperUpdate;
import com.javanauta.desafio3.bussines.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.bussines.dtos.response.AutoresResponse;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.exceptions.CPFObrigatorio;
import com.javanauta.desafio3.infrasctruture.exceptions.IdNaoEncontrado;
import com.javanauta.desafio3.infrasctruture.repositories.AutoresRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutoresRepository autoresRepository;
    private final Mapper mapper;
    private final MapperUpdate mapperUpdate;



    // metodo criar autor
    public AutoresResponse criarAutor(AutoresRequest request){

        verificarOrigem(mapper.paraEntity(request));

        return mapper.autoresResponse(
                autoresRepository.saveAndFlush(mapper.paraEntity(request)));

    }

    // metodo atualizar autor
    public AutoresResponse atualizarAutor(AutoresRequest request, Long id){

        AutoresEntity autorNoBanco = buscarAutor(id);

        AutoresEntity autorAtualizado = mapperUpdate.atualizarDtoResponse(request,autorNoBanco);

        return mapper.autoresResponse(autoresRepository.save(autorAtualizado));
    }

    // metodo para buscar todos autores
    public List<AutoresResponse> buscarTodosAutores(){

       return mapper.listaAutoresResponse(autoresRepository.findAll());
    }

    // metodo vizualizar autor pelo id
    public AutoresResponse vizualizarAutorPeloId(Long id){

        return mapper.autoresResponse(buscarAutor(id));
    }

    // metodo para excluir autor
    public void excluirAutorPeloId(Long id){

        autoresRepository.deleteById(id);
    }

    // metodo para buscar autor no banco de dados
    public AutoresEntity buscarAutor(Long id){

        return autoresRepository.findById(id).orElseThrow(
                ()-> new IdNaoEncontrado("Id não Encontrado" + id)
        );

    }

    // metodo para verificar se o pais_origem é brasil
    public void verificarOrigem(AutoresEntity autores){

        if("Brasil".equalsIgnoreCase(autores.getPais_origem())){

            String cpf = autores.getCpf();

            if(cpf == null || cpf.isEmpty()){

               throw  new CPFObrigatorio("Cpf para Brasileiro e obrigatorio");
            }

        }
    }

}
