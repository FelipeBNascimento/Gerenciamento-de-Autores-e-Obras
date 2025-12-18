package com.javanauta.desafio3.bussines.services;


import com.javanauta.desafio3.bussines.converter.Mapper;
import com.javanauta.desafio3.bussines.converter.MapperUpdate;
import com.javanauta.desafio3.bussines.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.bussines.dtos.response.AutoresResponse;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.exceptions.CPFObrigatorio;
import com.javanauta.desafio3.infrasctruture.exceptions.IdNaoEncontrado;
import com.javanauta.desafio3.infrasctruture.repositories.AutoresRepository;
import com.javanauta.desafio3.infrasctruture.security.JwtUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Random;

@Validated
@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutoresRepository autoresRepository;
    private final Mapper mapper;
    private final MapperUpdate mapperUpdate;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;



    // metodo criar autor
    public AutoresResponse criarAutor(AutoresRequest request) {

        // converter a requisição em uma entity
        AutoresEntity entity = mapper.paraEntity(request);

        // verificar a origem fazendo a logica de negocio
        verificarOrigem(entity);

        // converter o vpf em um hash estou considerando cpf como senha
        entity.setCpf(encoder.encode(entity.getCpf()));

        // salvar no banco de daods novo autor
        AutoresEntity usuarioSalvar =  autoresRepository.save(entity);

        // converter para response o usuario salvo que era uma entity
        return mapper.autoresResponse(usuarioSalvar);
    }

    // metodo atualizar autor
    public AutoresResponse atualizarAutor(AutoresRequest request, Long id) {

        // busco o autor pelo id
        AutoresEntity autorNoBanco = buscarAutor(id);

        // atualizo o autor e converte para entity
        AutoresEntity autorAtualizado = mapperUpdate.atualizarAutorParaEntity(request, autorNoBanco);

        // verificar a origem se foi alterada
        verificarOrigem(autorAtualizado);

        // faz o retorno em response ja convertendo de entotyt para response
        return mapper.autoresResponse(autoresRepository.save(autorAtualizado));
    }

    // metodo para buscar todos autores
    public List<AutoresResponse> buscarTodosAutores() {

        // busca todos os autores no banco ja convertido de entity para response
        return mapper.listaAutoresResponse(autoresRepository.findAll());
    }

    // metodo vizualizar autor pelo id
    public AutoresResponse vizualizarAutorPeloId(Long id) {

        // biscar pelo id o autor e ja vizualiza ja convertendo entity para response
        return mapper.autoresResponse(buscarAutor(id));
    }

    // metodo para excluir autor
    public void excluirAutorPeloId(Long id) {

        autoresRepository.deleteById(id);
    }

    // metodo para buscar autor no banco de dados
    public AutoresEntity buscarAutor(Long id) {

        return autoresRepository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Id não Encontrado" + id)
        );

    }

    // metodo para verificar se o pais_origem é brasil
    public void verificarOrigem(AutoresEntity autores) {

        // verificae a nacionalidade se e brasileiro
        if ("Brasil".equalsIgnoreCase(autores.getPais_origem())) {

            // verfifica se o cpf nao esta vazio
            if (autores.getCpf() == null || autores.getCpf().isEmpty()) {

                // gera a excessão
                throw new CPFObrigatorio("Cpf para Brasileiro e obrigatorio");
            }
        }
            else {
                // esse else seria somente para estrangeiro que se nao
            // passar o cpf gerarar um automatico com 11 digitos
            // somente brasileiro e obrigado a passar
                if (autores.getCpf() == null || autores.getCpf().isEmpty()) {

                    Random gerar = new Random();

                    StringBuilder numeros = new StringBuilder();

                    for (int i = 0; i < 11; i++) {

                        int numero = gerar.nextInt(10);
                        numeros.append(numero);
                    }
                    String cpfAleatorio = numeros.toString();
                    autores.setCpf(cpfAleatorio);

                }
            }
        }

    }

