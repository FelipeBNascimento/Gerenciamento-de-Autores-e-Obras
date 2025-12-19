package com.javanauta.desafio3.business.services;

import com.javanauta.desafio3.business.converter.Mapper;
import com.javanauta.desafio3.business.converter.MapperUpdate;
import com.javanauta.desafio3.business.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.business.dtos.requests.ObrasRequests;
import com.javanauta.desafio3.business.dtos.response.AutoresResponse;
import com.javanauta.desafio3.business.dtos.response.ObrasResponse;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import com.javanauta.desafio3.infrasctruture.exceptions.CPFObrigatorio;
import com.javanauta.desafio3.infrasctruture.exceptions.IdNaoEncontrado;
import com.javanauta.desafio3.infrasctruture.repositories.AutoresRepository;
import com.javanauta.desafio3.infrasctruture.repositories.ObrasRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObrasServiceTest {


    @InjectMocks
    private ObrasService service;

    @Mock
    private ObrasRepository repository;

    @Mock
    private Mapper mapper;

    @Mock
    private MapperUpdate mapperUpdate;

    @Mock
    private AutoresRepository autoresRepository;

    @Mock
    private AutorService autorService;

    @Mock
    private PasswordEncoder encoder;

    private AutoresEntity autorEntity() {

        AutoresEntity entity = new AutoresEntity();
        entity.setId(1L);
        entity.setNome("Felipe");
        entity.setSexo("Masculino");
        entity.setPais_origem("Brasil");
        entity.setEmail("teste@teste.com.br");
        entity.setData_nascimento(LocalDate.of(2000, 12, 12));
        entity.setCpf("12345678910");
        entity.setSenha("123456");
        return entity;
    }

    private AutoresRequest autorRequest() {

        AutoresRequest autoresRequest = new AutoresRequest(
                "Felipe", "Masculino", "teste@teste.com.br", LocalDate.of(2000, 12, 12),
                "Brasil", "12345678910", "123456");

        return autoresRequest;
    }

    private AutoresResponse autorResponse() {

        AutoresResponse autoresResponse = new AutoresResponse(
                1L, "Felipe", "Masculino", "teste@teste.com.br", LocalDate.of(2000, 12, 12),
                "Brasil", "12345678910");

        return autoresResponse;

    }

    private ObrasEntity obra() {

        List<AutoresEntity> listaAutores = new ArrayList<>();

        listaAutores.add(autorEntity());

        ObrasEntity obra = new ObrasEntity();

        obra.setId(1L);
        obra.setNome("Obra Teste");
        obra.setDescricao("Essa é uma obra para teste");
        obra.setData_publicacao(LocalDate.of(2025, 12, 18));
        obra.setAutores(listaAutores);
        return obra;

    }

    private ObrasResponse obrasResponse() {

        List<AutoresResponse> lista = new ArrayList<>();
        lista.add(autorResponse());

        ObrasResponse obras = new ObrasResponse(
                1L, "Obra Teste", "Essa é uma obra para teste",
                LocalDate.of(2025, 12, 18), lista);

        return obras;
    }

    private ObrasRequests obrasRequests() {

        List<AutoresRequest> lista = new ArrayList<>();
        lista.add(autorRequest());

        ObrasRequests obrasRequests = new ObrasRequests(
                "Obra teste", "Essa é uma obra para teste",
                LocalDate.of(2025, 12, 18), lista
        );

        return obrasRequests;
    }

    @Test
    void criarUmaObra() {

        ObrasEntity obra = obra();
        ObrasRequests obrasRequests = obrasRequests();
        ObrasResponse obrasResponse = obrasResponse();

        when(mapper.obrasEntity(obrasRequests)).thenReturn(obra);
        when(repository.save(obra)).thenReturn(obra);
        when(mapper.obrasResponse(obra)).thenReturn(obrasResponse);

        ObrasResponse resultado = service.cadastraObra(obrasRequests);

        Mockito.verify(repository, Mockito.times(1)).save(obra);

        Assertions.assertNotNull(resultado);

    }

    @Test
    void mostrarUmErroAoBuscarUmaObraPeloId() {

        ObrasEntity obra = obra();
        ObrasRequests obrasRequests = obrasRequests();
        ObrasResponse obrasResponse = obrasResponse();

        Long idInexistente = 2L;

        when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        Assertions.assertThrows(IdNaoEncontrado.class,
                () -> {
                    service.obraPeloId(idInexistente);
                }, "Id não encontrado");


    }

    @Test
    void deveLancarUmErroDeCpfParaBrasileiro() {

        AutoresRequest autoresRequest = new AutoresRequest(
                "Felipe", "Masculino", "teste@teste.com.br", LocalDate.of(2000, 12, 12),
                "Brasil", null, "123456");

        List<AutoresRequest> listaRequest = new ArrayList<>();
        listaRequest.add(autoresRequest);

        ObrasRequests obrasRequests = new ObrasRequests(
                "Obra teste", "Essa é uma obra para teste",
                LocalDate.of(2025, 12, 18), listaRequest
        );


        ObrasEntity obras = obra();
        AutoresEntity autores = autorEntity();
        autores.setCpf(null);
        List<AutoresEntity> lista = new ArrayList<>();
        lista.add(autores);
        obras.setAutores(lista);

        when(mapper.obrasEntity(obrasRequests)).thenReturn(obras);

        Mockito.doThrow(new CPFObrigatorio("Cpf para Brasileiro e obrigatorio"))
                .when(autorService).verificarOrigem(Mockito.any(AutoresEntity.class));

        Assertions.assertThrows(CPFObrigatorio.class,
                () -> {
                    service.cadastraObra(obrasRequests);
                }, "Cpf para Brasileiro e obrigatorio");


    }


}