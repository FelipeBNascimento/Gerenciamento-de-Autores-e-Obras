package com.javanauta.desafio3.bussines.dtos.requests;

import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public record ObrasRequests (String nome, String descricao,
                             LocalDate data_publicacao, List<AutoresEntity> autoresEntities){
}
