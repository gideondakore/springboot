package com.example.quickstart.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(
                                (request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN")
                        )
                        .authenticationEntryPoint(
                        (request, response, authenticationException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication required")
                ));

        return http.build();
    }
}
