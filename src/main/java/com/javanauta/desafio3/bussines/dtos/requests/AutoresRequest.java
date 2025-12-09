package com.javanauta.desafio3.bussines.dtos.requests;

import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;

import java.time.LocalDate;
import java.util.List;

public record AutoresRequest  (String nome,
                               String sexo,
                               String email,
                               LocalDate data_nascimento,
                               String pais_origem,
                               String cpf,
                               List<ObrasEntity> obrasEntities) {
}

