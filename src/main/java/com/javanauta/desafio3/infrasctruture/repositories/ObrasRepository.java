package com.javanauta.desafio3.infrasctruture.repositories;

import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObrasRepository extends JpaRepository<ObrasEntity, Long> {
}
