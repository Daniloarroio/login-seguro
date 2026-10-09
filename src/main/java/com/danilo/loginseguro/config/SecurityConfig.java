package com.danilo.loginseguro.config;

import com.danilo.loginseguro.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .userDetailsService(customUserDetailsService)

            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/cadastro")
            )

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/cadastro",
                    "/css/**",
                    "/js/**",
                    "/login"
                ).permitAll()

                .requestMatchers("/usuarios/**")
                .hasRole("ADMIN")

                .requestMatchers("/produtos/**")
                .hasAnyRole("ADMIN", "SYSTEM")

                .requestMatchers("/compras/**")
                .hasAnyRole("ADMIN", "USER")

                .anyRequest()
                .authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login")
            );

        return http.build();
    }
}