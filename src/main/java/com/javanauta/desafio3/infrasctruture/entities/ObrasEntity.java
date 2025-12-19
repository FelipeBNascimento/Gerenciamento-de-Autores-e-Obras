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
@Table(name = "obras")

public class ObrasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 240)
    private String descricao;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate data_publicacao;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable (
            name = "tabela_ligacao",
            joinColumns = @JoinColumn (name = "obras_id"),
            inverseJoinColumns = @JoinColumn(name = "autores_id")
    )
    private List<AutoresEntity> autores;

}
