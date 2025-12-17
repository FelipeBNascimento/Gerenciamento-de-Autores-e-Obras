package com.javanauta.desafio3.infrasctruture.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // Desativa proteção CSRF para APIs REST (não aplicável a APIs que não mantêm estado)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/obras").permitAll()// Não Permite acesso ao endpoint GET sem autenticação
                        .requestMatchers(HttpMethod.DELETE, "/obras").authenticated() // Não Permite acesso ao endpoint DELETE sem autenticação
                        .requestMatchers(HttpMethod.POST, "/obras/**").permitAll()// Permite acesso ao endpoint POST /usuario sem autenticação
                        .requestMatchers(HttpMethod.POST, "/autor/**").permitAll()
                        .anyRequest().permitAll() // Permite para todas as outras requisições
                )
                .httpBasic(httpBasic -> {}); // Habilita autenticação Http Bascic, pedindo usuario e senha no cabeçalho Authorization


        // Retorna a configuração do filtro de segurança construída
        return http.build();
    }

}
