package com.javanauta.desafio3.bussines.services;

import com.javanauta.desafio3.bussines.converter.Mapper;
import com.javanauta.desafio3.bussines.converter.MapperUpdate;
import com.javanauta.desafio3.bussines.dtos.response.ObrasResponse;
import com.javanauta.desafio3.infrasctruture.exceptions.EmailExistente;
import com.javanauta.desafio3.infrasctruture.exceptions.IdNaoEncontrado;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import com.javanauta.desafio3.infrasctruture.repositories.AutoresRepository;
import com.javanauta.desafio3.infrasctruture.repositories.ObrasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository repository;
    private final Mapper mapper;
    private final MapperUpdate mapperUpdate;
    private final AutoresRepository autoresRepository;
    private final AutorService autorService;


    public ObrasResponse cadastraObra(ObrasEntity obras) {

        // criando uma lista para depois verificar de autores
        List<AutoresEntity> listaAutores = obras.getAutores();

        // verificando a lista se existe algum email
        verificandoAutor(listaAutores);

        // verificando a origem do autores
        for (AutoresEntity autores : listaAutores){

            autorService.verificarOrigem(autores);
        }


        // salvando a lista
        return mapper.obrasResponse(repository.save(obras));
    }

    public ObrasResponse cadastrarObrasComAutorExistente(ObrasEntity obras, Long id){

        // Buscando autor pelo id
        AutoresEntity autor = buscarAutorpeloId(id);

        // Criando uma lista nova de autores
        List<AutoresEntity> listaAutores = new ArrayList<>();

        // adicionando o autor na lista
        listaAutores.add(autor);

        // passando o ator existente a lista de autores
        obras.setAutores(listaAutores);

        return mapper.obrasResponse(repository.save(obras));
    }

    // verificar se nao existes algum autor
    public void verificandoAutor(List<AutoresEntity> autoresEntity) {


        for (AutoresEntity autores : autoresEntity) {

            String emailAutores = autores.getEmail();

            boolean existeEmail = autoresRepository.existsByEmail(emailAutores);

            if (existeEmail) {

                throw new EmailExistente("Email ja cadastrado");
            }


        }
    }

    // metodo criado para buscar um autor
    public AutoresEntity buscarAutorpeloId(Long id){

        return autoresRepository.findById(id).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );
    }


    // metodo criado para buscar no banco de dados pelo id
    public ObrasResponse obraPeloId(Long id) {

        ObrasEntity entity = repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id não encontrado")
        );

        return mapper.obrasResponse(entity);
    }

    // apagar a obra do sistema
    public void apagarObraPeloId(Long id){

        repository.deleteById(id);
    }


}
