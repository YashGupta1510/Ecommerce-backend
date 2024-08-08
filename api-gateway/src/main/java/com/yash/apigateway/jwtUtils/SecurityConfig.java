package com.yash.apigateway.jwtUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf->csrf.disable()) 
            .cors(cors->cors.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll() // Allow access to auth endpoints without authentication
                    .requestMatchers("/home/**").permitAll()
                    .anyRequest().authenticated()) // All other endpoints require authentication
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}