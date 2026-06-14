package com.example.portfolio_backend.config;

import com.example.portfolio_backend.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // Login API
                        .requestMatchers("/auth/login").permitAll()

                        // Public project APIs
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/projects").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/projects/**").permitAll()

                        // Public activity APIs
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/activities").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/activities/**").permitAll()

                        // Public feedback submit/view APIs
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/projects/*/feedback").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/projects/*/feedback/approved").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/activities/*/feedback").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/activities/*/feedback/approved").permitAll()

                        // Public contact form
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/contact").permitAll()

                        // Admin APIs
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Project write APIs
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/projects").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/projects/**").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/projects/**").hasRole("ADMIN")

                        // Activity write APIs
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/activities").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/activities/**").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/activities/**").hasRole("ADMIN")

                        // Everything else needs login
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}