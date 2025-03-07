package com.patrones.jwt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/public").permitAll() // Endpoint público
                        .requestMatchers("/api/admin").hasRole("ADMIN") // Solo para Admin
                        .requestMatchers("/api/user").hasRole("USER")   // Solo para User
                        .anyRequest().authenticated()
                )
                // Configuracion JWT
                .httpBasic();

        return http.build();
    }



}
