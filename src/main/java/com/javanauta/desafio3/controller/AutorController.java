package com.javanauta.desafio3.controller;

import com.javanauta.desafio3.business.dtos.requests.AutoresRequest;
import com.javanauta.desafio3.business.dtos.response.AutoresResponse;
import com.javanauta.desafio3.business.services.AutorService;
import com.javanauta.desafio3.infrasctruture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autor")
public class AutorController {

    private final AutorService service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login (@RequestBody AutoresRequest request){

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.email(),request.senha()));

        return "Bearer " +jwtUtil.generateToken(authentication.getName());
    }



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
