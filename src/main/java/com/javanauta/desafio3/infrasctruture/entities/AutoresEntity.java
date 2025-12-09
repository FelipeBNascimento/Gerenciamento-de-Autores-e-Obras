package com.javanauta.desafio3.infrasctruture.entities;


import jakarta.persistence.*;
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

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    @Column(name = "pais_origem")
    private String pais_origem;

    @Column(name = "cpf", unique = true)
    private String cpf;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "obras_id", referencedColumnName = "id")
    private List<ObrasEntity> obrasEntity;
}
