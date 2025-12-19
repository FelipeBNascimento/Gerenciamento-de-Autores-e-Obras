package com.javanauta.desafio3.business.dtos.requests;

import java.time.LocalDate;
import java.util.List;


public record ObrasRequests (String nome, String descricao,
                             LocalDate data_publicacao,
                             List<AutoresRequest> autores){
}
