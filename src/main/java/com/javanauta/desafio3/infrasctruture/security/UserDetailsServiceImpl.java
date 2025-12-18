package com.javanauta.desafio3.infrasctruture.security;


import com.javanauta.desafio3.infrasctruture.entities.AutoresEntity;
import com.javanauta.desafio3.infrasctruture.repositories.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositório para acessar dados de autor no banco de dados
    @Autowired
    private AutoresRepository autoresRepository;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o autor no banco de dados pelo e-mail
        AutoresEntity autor = autoresRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Autor não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no autor encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(autor.getEmail()) // Define o nome de autor como o e-mail
                .password(autor.getSenha()) // Define a senha como o o campo senha do autor
                .build(); // Constrói o objeto UserDetails
    }
}
