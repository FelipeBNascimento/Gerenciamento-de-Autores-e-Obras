package com.javanauta.desafio3.infrasctruture.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "autores")
public class AutoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Email(message = "Por favor insira um email valido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Past(message = "A data de nascimento deve ser no passado")
    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    @Column(name = "pais_origem", nullable = false)
    private String pais_origem;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @ManyToMany(mappedBy = "autores")
    private List<ObrasEntity> obras;
}
