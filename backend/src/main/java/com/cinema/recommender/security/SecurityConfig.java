package com.cinema.recommender.security;

import com.cinema.recommender.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String SECRET_KEY =
            "cinema_secret_key_123456_very_strong_key_2026";

    private final SecurityExceptionHandler securityExceptionHandler;

    public SecurityConfig(SecurityExceptionHandler securityExceptionHandler) {
        this.securityExceptionHandler = securityExceptionHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(securityExceptionHandler)
                        .accessDeniedHandler(securityExceptionHandler)
                )
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers(HttpMethod.GET,
                                "/api/movies/**",
                                "/api/showtimes/**",
                                "/api/seats/**"
                        ).permitAll()
                        .requestMatchers("/api/auth/**").permitAll()

                        // USER
                        .requestMatchers("/api/bookings/**", "/api/promotions/**")
                        .hasRole("USER")

                        // ADMIN
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(SECRET_KEY),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}


