package com.javanauta.desafio3.controller;

import com.javanauta.desafio3.business.dtos.requests.ObrasRequests;
import com.javanauta.desafio3.business.dtos.response.ObrasResponse;
import com.javanauta.desafio3.business.services.ObrasService;
import com.javanauta.desafio3.infrasctruture.entities.ObrasEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/obras")
public class ObrasController {

    private final ObrasService service;


    @PostMapping
    public ResponseEntity<ObrasResponse> cadastraObras(@RequestBody ObrasRequests obras){

        service.cadastraObra(obras);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/id_autores")
    public ResponseEntity<ObrasResponse> cadastrarObrasComAutoresNoBanco(@RequestBody ObrasEntity obras,
                                                                       @RequestParam Long id_autor){
        return ResponseEntity.ok(service.cadastrarObrasComAutorExistente(obras,id_autor));

    }

    @GetMapping
    public ResponseEntity<ObrasResponse> visualizarObraPeloId (@RequestParam Long id){

        return ResponseEntity.ok(service.obraPeloId(id));
    }

    @DeleteMapping
    public ResponseEntity deletarObraPeloId(@RequestParam long id){

        service.apagarObraPeloId(id);

        return ResponseEntity.ok().build();
    }



}
