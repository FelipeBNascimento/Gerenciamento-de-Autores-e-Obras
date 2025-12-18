package com.javanauta.desafio3.infrasctruture.repositories;

import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoresRepository extends JpaRepository<AutoresEntity, Long> {

    boolean existsByEmail(String email);

    Optional<AutoresEntity> findByEmail(String email);

}
