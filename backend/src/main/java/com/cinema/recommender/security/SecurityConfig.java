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
@EnableMethodSecurity
public class SecurityConfig {

    private static final String SECRET_KEY = "cinema_secret_key_123456";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // 1️⃣ AUTH – PUBLIC
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2️⃣ PUBLIC GET APIs
                        .requestMatchers(HttpMethod.GET,
                                "/api/movies/**",
                                "/api/showtimes/**",
                                "/api/seats/**"
                        ).permitAll()

                        // 3️⃣ USER APIs
                        .requestMatchers(
                                "/api/bookings/**",
                                "/api/promotions/**"
                        ).hasAnyRole("USER", "STAFF", "ADMIN")

                        // 4️⃣ STAFF APIs
                        .requestMatchers("/api/staff/**")
                        .hasAnyRole("STAFF", "ADMIN")

                        // 5️⃣ ADMIN APIs
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

