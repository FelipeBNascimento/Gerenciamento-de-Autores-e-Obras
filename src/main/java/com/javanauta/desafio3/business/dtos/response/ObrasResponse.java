package com.javanauta.desafio3.business.dtos.response;

import java.time.LocalDate;
import java.util.List;


public record ObrasResponse (Long id, String nome, String descricao,
                             LocalDate data_publicacao, List<AutoresResponse> autores ){
}


