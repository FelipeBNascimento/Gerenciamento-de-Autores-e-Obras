package com.javanauta.desafio3.controller;

import com.javanauta.desafio3.bussines.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.bussines.dtos.response.AutoresResponse;
import com.javanauta.desafio3.bussines.services.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autor")
public class AutorController {

    private final AutorService service;

    @PostMapping
    public ResponseEntity<AutoresResponse> criarAutor(@RequestBody AutoresRequest request){


        return ResponseEntity.ok(
                service.criarAutor(request));
    }

    @GetMapping("/id_autor")
    public ResponseEntity<AutoresResponse> buscarAutorPeloID(@RequestParam Long id){

        return ResponseEntity.ok(service.vizualizarAutorPeloId(id));
    }

    @GetMapping
    public ResponseEntity<List<AutoresResponse>> buscarTodosAutores(){

        return ResponseEntity.ok(service.buscarTodosAutores());
    }

    @PutMapping
    public ResponseEntity<AutoresResponse> atualizarAutor(@RequestBody AutoresRequest autoresRequest,
                                                          @RequestParam Long id){

        return ResponseEntity.ok
                (service.atualizarAutor(autoresRequest,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarAutorPeloID(@PathVariable("id") Long id){

        service.excluirAutorPeloId(id);

        return ResponseEntity.ok().build();
    }
}
