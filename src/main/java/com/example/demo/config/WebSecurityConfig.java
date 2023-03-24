package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/"));
        corsConfiguration.setAllowedMethods(List.of("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:3000/*"));
        corsConfiguration.setExposedHeaders(List.of("Authorization"));


        http.authorizeHttpRequests()
                .requestMatchers("/api/books/**", "/api/categories/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .cors().configurationSource(request -> corsConfiguration);

        http.oauth2ResourceServer()
                .jwt();


        return http.build();
    }

}
