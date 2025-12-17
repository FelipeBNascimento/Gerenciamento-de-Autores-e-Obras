package com.javanauta.desafio3.bussines.dtos.response;

import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public record ObrasResponse (Long id, String nome, String descricao,
                             LocalDate data_publicacao, List<AutoresResponse> autores ){
}


