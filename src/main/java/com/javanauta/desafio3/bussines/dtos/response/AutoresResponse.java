package com.javanauta.desafio3.bussines.dtos.response;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import java.time.LocalDate;
import java.util.List;


public record AutoresResponse (Long id,
                              String nome,
                              String sexo,
                              String email,
                              LocalDate data_nascimento,
                              String pais_origem,
                              String cpf) {
}

