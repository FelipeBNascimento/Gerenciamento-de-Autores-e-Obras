package com.javanauta.desafio3.business.dtos.response;
import java.time.LocalDate;


public record AutoresResponse (Long id,
                              String nome,
                              String sexo,
                              String email,
                              LocalDate data_nascimento,
                              String pais_origem,
                              String cpf) {
}

