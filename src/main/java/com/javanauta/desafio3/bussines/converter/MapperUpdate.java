package com.javanauta.desafio3.bussines.converter;

import com.javanauta.desafio3.bussines.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.bussines.dtos.response.AutoresResponse;
import com.javanauta.desafio3.bussines.dtos.response.ObrasResponse;
import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperUpdate {

    AutoresEntity atualizarDtoResponse(AutoresRequest request, @MappingTarget AutoresEntity entity);

    void atualizarDtoObrasResponse(ObrasResponse dto, @MappingTarget ObrasEntity entity);

}
