package com.javanauta.desafio3.bussines.dtos.requests;

import java.time.LocalDate;

public record AutoresRequest  (String nome,
                               String sexo,
                               String email,
                               LocalDate data_nascimento,
                               String pais_origem,
                               String cpf) {
}

