package com.javanauta.desafio3.business.converter;

import com.javanauta.desafio3.business.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.business.dtos.requests.ObrasRequests;
import com.javanauta.desafio3.business.dtos.response.AutoresResponse;
import com.javanauta.desafio3.business.dtos.response.ObrasResponse;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    AutoresEntity paraEntity(AutoresRequest request);

    AutoresResponse autoresResponse(AutoresEntity entity);

    ObrasEntity obrasEntity (ObrasRequests requests);

    ObrasResponse obrasResponse (ObrasEntity entity);

     AutoresRequest autoresRequest(AutoresEntity entity);

     List<AutoresResponse> listaAutoresResponse(List<AutoresEntity> autoresEntities);
}
